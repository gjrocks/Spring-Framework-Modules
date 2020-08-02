# user-details-service demo

1. mvn clean
2. mvn test
3. mvn clean install 
4. Go to the target folder
5. java -Dlog.location=C:/temp/log -jar target/user-details-service-0.0.1-SNAPSHOT.jar
6. Verify your RESTful calls.
7.http://localhost:8000/user and http://localhost:8000/ping
8. Please note the application.properties file in parent folder is used, so to change port etc use that.
see this 
https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html#Application Property Files
