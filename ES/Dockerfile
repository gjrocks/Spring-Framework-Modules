# Prepared by Ganesh Jadhav

FROM seeaganesh/java8-172-centos

MAINTAINER Ganesh Jadhav, seeaganesh@gmail.com

ADD ./target/es-0.0.1-SNAPSHOT.jar /opt/ganesh-work

#ADD ./target/application.properties /opt/ganesh-work



CMD /usr/java/latest/bin/java -Dlog.location=/opt/ganesh-work/log -jar /opt/ganesh-work/es-0.0.1-SNAPSHOT.jar