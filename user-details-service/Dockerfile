# Prepared by Ganesh Jadhav

FROM amazoncorretto:8u265

MAINTAINER Ganesh Jadhav, ganesh.jadhav@mastek.com

RUN mkdir -p /opt/ganesh-work

ADD ./target/user-details-service-0.0.1-SNAPSHOT.jar /opt/ganesh-work

ADD ./application.properties /opt/ganesh-work

#it is expected to come from docker run command docker run -e CONSUL_IP=0.0.0.0 <container name>
#CMD export SPRING_CLOUD_CONSUL_HOST=$CONSUL_IP

EXPOSE 7000
EXPOSE 7001

RUN echo $JAVA_HOME
CMD $JAVA_HOME/bin/java -Dspring.cloud.consul.host=$CONSUL_IP -Dlog.location=/opt/ganesh-work/log -jar /opt/ganesh-work/user-details-service-0.0.1-SNAPSHOT.jar
