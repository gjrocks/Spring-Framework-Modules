#!/bin/sh
#
# Startup script for the Tomcat Web Server
#
# In general tomcat should always be started BEFORE apache
# chkconfig: - 84 14
# description: tomcat
# Source function library.
#

JAVA_HOME=/opt/ebulk/jdk1.8.0_152
TOMCAT_HOME=/opt/ebulk/apache-tomcat-8.5.23
EBULK_HOME=/opt/ebulk
PATH="$JAVA_HOME/bin:$PATH"

CATALINA_OPTS="-Xmx2560m -Xms512m -XX:MaxMetaspaceSize=1024m -XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:StartFlightRecording=duration=60m,delay=120s,filename='/opt/ebulk/config/cheqs_dev/myrecording.jfr'  -XX:+UseConcMarkSweepGC"
#CATALINA_OPTS="-Xmx2048m -Xms256m -XX:MaxMetaspaceSize=512m"
#CATALINA_OPTS="-Xmx2048m -Xms256m -XX:MaxMetaspaceSize=512m -XX:+UnlockCommercialFeatures -XX:+FlightRecorder"

JAVA_OPTS="-Dsystem.config.dir=file:/opt/ebulk/config -Dfile.encoding=UTF-8 -Djava.awt.headless=true -server"

CLASSPATH=$CLASSPATH:.:./resources/lib/:./resources/lib/acegi-security-1.0.7.jar:./resources/lib/bcprov-jdk16-141.jar:./resources/lib/commons-cli-1.2.jar:./resources/lib/commons-codec.jar:./resources/lib/commons-compiler-2.7.8.jar:./resources/lib/commons-jexl.jar:./resources/lib/commons-lang-2.4.jar:./resources/lib/commons-logging.jar:./resources/lib/cryptix-jce-provider.jar:./resources/lib/cryptix-message-api.jar:./resources/lib/cryptix-openpgp-provider.jar:./resources/lib/cryptix-pki-api.jar:./resources/lib/janino-2.7.8.jar:./resources/lib/jasypt-1.5.jar:./resources/lib/mysql-connector-java-5.1.40-bin.jar:./resources/lib/scriptella-core.jar:./resources/lib/scriptella-drivers.jar:./resources/lib/scriptella-tools.jar:./resources/lib/scriptella.jar:./resources/lib/spring-beans-3.0.5.RELEASE.jar:./resources/lib/spring-core-3.0.5.RELEASE.jar:./resources/lib/velocity-dep-1.4.jar:./bin:./resources/etl

LD_LIBRARY_PATH="$TOMCAT_HOME/bin:$TOMCAT_HOME/lib:/usr/local/apr/lib"
export JAVA_HOME PATH TOMCAT_HOME CATALINA_OPTS LD_LIBRARY_PATH JAVA_OPTS CLASSPATH

# See how we were called.

#echo "ENSURE YOU HAVE BACKED UP THE TARGET DATABASE BEFORE RUNNING THIS UPGRADE"
#echo "Please enter location of the config :"
#read fld
#echo "Please enter deployment name:"
#read site

#echo "If you want to change the password, then please provide the password, otherwise just hit enter:"
#read newpass

#test -d $fld/$site
#if [ $? -ne 0 ];
#then
#echo "$fld/$site is not a valid folder -exiting" 
#exit 1
#fi
    

#echo 'Updating the  $site'

fld=$1
site=$2
newpass=$3

java com.gj.Test $fld $site $newpass
#java -version










