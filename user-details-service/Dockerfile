# Prepared by Ganesh Jadhav

#FROM amazoncorretto:8u265
#3.8.1-amazoncorretto-8, 3.8-amazoncorretto-8, 3-amazoncorretto-8
FROM maven:3.8.1-amazoncorretto-8 as appbuild
MAINTAINER Ganesh Jadhav, seeaganesh@gmail.com

RUN mkdir -p /opt/app
WORKDIR /opt/app
ADD ./pom.xml ./pom.xml

RUN mvn dependency:go-offline -B

COPY ./src ./src
COPY ./newrelic ./newrelic

RUN mvn package && cp target/user-details-service-0.0.1-SNAPSHOT.jar app.jar

FROM amazoncorretto:8u265

WORKDIR /opt/app
#RUN cd /opt/ganesh-work
#RUN ls -l
#RUN mvn clean package

#it is expected to come from docker run command docker run -e CONSUL_IP=0.0.0.0 <container name>
#CMD export SPRING_CLOUD_CONSUL_HOST=$CONSUL_IP

COPY --from=appbuild /opt/app/app.jar ./app.jar
COPY --from=appbuild /opt/app/newrelic ./newrelic

EXPOSE 7000
EXPOSE 7001

RUN echo $JAVA_HOME
CMD $JAVA_HOME/bin/java -Dlog.location=/opt/app/log -javaagent:/opt/app/newrelic/newrelic.jar -jar /opt/app/app.jar
