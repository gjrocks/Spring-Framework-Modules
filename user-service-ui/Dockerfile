# Prepared by Ganesh Jadhav

FROM seeaganesh/java8-172-centos

MAINTAINER Ganesh Jadhav, ganesh.jadhav@mastek.com

ADD ./target/user-service-ui-1.0.war /opt/ganesh-work

#it is expected to come from docker run command docker run -e CONSUL_IP=0.0.0.0 <container name>
#CMD export SPRING_CLOUD_CONSUL_HOST=$CONSUL_IP


CMD /usr/java/latest/bin/java -Dspring.cloud.consul.host=$CONSUL_IP -Dlog.location=/opt/ganesh-work/log -jar /opt/ganesh-work/user-service-ui-1.0.war