REM Example: etl.bat C:\Apps\capita-apps-config\ebulk\sites cheqs

set JAVA_HOME=C:/jdk1.8.0_172
set PATH=%JAVA_HOME%\bin;%PATH%
set classpath=.;./resources/lib/;./resources/lib/acegi-security-1.0.7.jar;./resources/lib/bcprov-jdk16-141.jar;./resources/lib/commons-cli-1.2.jar;./resources/lib/commons-codec.jar;./resources/lib/commons-compiler-2.7.8.jar;./resources/lib/commons-jexl.jar;./resources/lib/commons-lang-2.4.jar;./resources/lib/commons-logging.jar;./resources/lib/cryptix-jce-provider.jar;./resources/lib/cryptix-message-api.jar;./resources/lib/cryptix-openpgp-provider.jar;./resources/lib/cryptix-pki-api.jar;./resources/lib/janino-2.7.8.jar;./resources/lib/jasypt-1.5.jar;./resources/lib/mysql-connector-java-5.1.40-bin.jar;./resources/lib/scriptella-core.jar;./resources/lib/scriptella-drivers.jar;./resources/lib/scriptella-tools.jar;./resources/lib/scriptella.jar;./resources/lib/spring-beans-3.0.5.RELEASE.jar;./resources/lib/spring-core-3.0.5.RELEASE.jar;./resources/lib/velocity-dep-1.4.jar;./bin;./resources/etl

java -version
echo %1
echo %2

java com.gj.Test %1 %2