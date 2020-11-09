terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 2.70"
    }
  }
}

variable region_name {}
provider "aws" {
  profile = "default"
  region  = var.region_name
}

# Create a new AWS Instance #min : 10.0.0.0 and max 10.0.255.255
resource "aws_vpc" "gj_vpc" {
  cidr_block = "10.0.0.0/16"
  tags = {
    name = "gj_vpc"
  }
}
#min : 10.0.0.0 and max 10.0.0.255
resource "aws_subnet" "gj_subnet1" {
  vpc_id     = aws_vpc.gj_vpc.id
  cidr_block = "10.0.0.0/17"
  availability_zone = "ap-south-1b"
map_public_ip_on_launch ="true"
  tags = {
    name = "gj_subnet1"
  }
}
#availability_zone - (Optional) The availability zone where the subnet must reside.

#availability_zone_id - (Optional) The ID of the Availability Zone for the subnet.
#
#
resource "aws_subnet" "gj_subnet2" {
  vpc_id     = aws_vpc.gj_vpc.id
  cidr_block = "10.0.128.0/17"
  availability_zone = "ap-south-1c"
map_public_ip_on_launch ="true"
  tags = {
    name = "gj_subnet2"
  }
}

resource "aws_internet_gateway" "gj_internat_gateway" {
  vpc_id = aws_vpc.gj_vpc.id
  tags = {
    name = "gj_int_gatweay"
  }
}

resource "aws_route_table" "gj_route_table" {
  vpc_id = aws_vpc.gj_vpc.id
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.gj_internat_gateway.id
  }
  tags = {
    name = "gj_route_table"
  }
}

resource "aws_route_table_association" "gj_route_association1" {

  subnet_id      = aws_subnet.gj_subnet1.id
  route_table_id = aws_route_table.gj_route_table.id
}

resource "aws_route_table_association" "gj_route_association2" {

  subnet_id      = aws_subnet.gj_subnet2.id
  route_table_id = aws_route_table.gj_route_table.id
}

resource "aws_security_group" "gj_sec_grp" {
  name        = "gj_allow_tls"
  description = "gj_Allow TLS inbound traffic"
  vpc_id      = aws_vpc.gj_vpc.id

  ingress {
    from_port   = 443
    to_port     = 443
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 7000
    to_port     = 7000
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

}

resource "aws_ecr_repository" "gjrepo" {
  name                 = "gjrepo"
  image_tag_mutability = "MUTABLE"

  image_scanning_configuration {
    scan_on_push = true
  }
}

resource "aws_ecr_repository_policy" "gjrepo-policy" {
  repository = aws_ecr_repository.gjrepo.name

  policy = <<EOF
{
    "Version": "2008-10-17",
    "Statement": [
        {
            "Sid": "gj ecr polocy",
            "Effect": "Allow",
            "Principal": "*",
            "Action": [
                "ecr:GetDownloadUrlForLayer",
                "ecr:BatchGetImage",
                "ecr:BatchCheckLayerAvailability",
                "ecr:PutImage",
                "ecr:InitiateLayerUpload",
                "ecr:UploadLayerPart",
                "ecr:CompleteLayerUpload",
                "ecr:DescribeRepositories",
                "ecr:GetRepositoryPolicy",
                "ecr:ListImages",
                "ecr:DeleteRepository",
                "ecr:BatchDeleteImage",
                "ecr:SetRepositoryPolicy",
                "ecr:DeleteRepositoryPolicy"
            ]
        }
    ]
}
EOF
}
## following section assumes we have a ecr repo set
# setup a role, this role will help ECS task to make API requests to autorised  AWS service

data "aws_iam_policy_document" "gj_ecs_tasks_execution_role" {
  statement {
    actions = ["sts:AssumeRole"]

    principals {
      type        = "Service"
      identifiers = ["ecs-tasks.amazonaws.com"]
    }
  }
}

resource "aws_iam_role" "gj_ecs_tasks_execution_role" {
  name               = "gj-ecs-task-execution-role"
  assume_role_policy = data.aws_iam_policy_document.gj_ecs_tasks_execution_role.json
}

resource "aws_iam_role_policy_attachment" "ecs_tasks_execution_role" {
  role       = aws_iam_role.gj_ecs_tasks_execution_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonECSTaskExecutionRolePolicy"
}


## ECS task
resource "aws_ecs_task_definition" "gj_userservice_aws_ecs_task" {
  family                = "gj_userservice_aws_ecs_task"
  container_definitions = file("./ecs-task.json")
task_role_arn           = aws_iam_role.gj_ecs_tasks_execution_role.arn
execution_role_arn      = aws_iam_role.gj_ecs_tasks_execution_role.arn
network_mode            = "awsvpc"
cpu                = "512"
memory = "2048"
requires_compatibilities = [
    "FARGATE"]

}
##ECS cluster

resource "aws_ecs_cluster" "gj_userservice_cluster" {
  name = "gj_userservice_cluster"
}


resource "aws_lb_target_group" "gj-ecs-service-target-group" {
#  name        = "gj-ecs-service-target-group"
  name_prefix ="gj-ecs"
  port        = 7000
  protocol    = "HTTP"
  target_type = "ip"
  vpc_id      = aws_vpc.gj_vpc.id

  lifecycle {
     create_before_destroy = true
   }

  health_check {
    healthy_threshold = 3
    unhealthy_threshold = 10
    path="/ping"
    port =7000
    protocol="HTTP"
  }

  tags = {
    name = "gj-ecs-service-target-group"
  }
}

resource "aws_lb" "gj-ecs-alb" {
  name               = "gj-ecs-alb"
  internal           = false
  load_balancer_type = "application"
  security_groups    = [aws_security_group.gj_sec_grp.id]
  subnets            = [aws_subnet.gj_subnet1.id,aws_subnet.gj_subnet2.id]

  tags = {
    Environment = "devl"
  }
}

resource "aws_lb_listener" "gj-ecs-alb-listener" {
  load_balancer_arn = aws_lb.gj-ecs-alb.arn
  port              = "7000"
  protocol          = "HTTP"


  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.gj-ecs-service-target-group.arn
  }
}

resource "aws_ecs_service" "gj-user-service" {
  name            = "gj-user-service"
  cluster         = aws_ecs_cluster.gj_userservice_cluster.id
  task_definition = aws_ecs_task_definition.gj_userservice_aws_ecs_task.arn
  desired_count   = 1
  launch_type   =  "FARGATE"
  load_balancer {
    target_group_arn = aws_lb_target_group.gj-ecs-service-target-group.arn
    container_name   = "userservice"
    container_port   = 7000
  }
  network_configuration {
     security_groups = [
       aws_security_group.gj_sec_grp.id]
       subnets =[aws_subnet.gj_subnet1.id,aws_subnet.gj_subnet2.id]
       assign_public_ip ="true"
   }

}
