# user-details-service demo

1. mvn clean
2. mvn test
3. mvn clean install 
4. Go to the target folder
5. java -Dlog.location=C:/temp/log -jar target/user-details-service-0.0.1-SNAPSHOT.jar
6. Verify your RESTful calls.
7.http://localhost:7000/user and http://localhost:7000/ping
8. Please note the application.properties file in parent folder is used, so to change port etc use that.
see this 
https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html#Application Property Files

You can pass the port as env variable. 

java -Dlog.location=C:/temp/log -jar target/user-details-service-0.0.1-SNAPSHOT.jar --server.port=8001

To run the multiple instances of the same service on the same machine using different ports, you might need to pass the spring.datasource.url as well, since I am using in memory h2 DB, sharing the same DB for multiple instances  might cause a problem. So for testing only.

java -Dlog.location=C:/temp/log -jar target/user-details-service-0.0.1-SNAPSHOT.jar --server.port=8001 --spring.datasource.url=jdbc:h2:file:~/test1;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE

and 

java -Dlog.location=C:/temp/log -jar target/user-details-service-0.0.1-SNAPSHOT.jar --server.port=8002 --spring.datasource.url=jdbc:h2:file:~/test2;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE

There is a multistage dockerfile, 
    #docker build -t userservice/latest .
    #docker run -it -p 7000:7000 userservice/latest
    
