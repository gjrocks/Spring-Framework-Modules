# spring Todo

1. mvn clean
2. mvn test
3. mvn clean install 
4. Go to the target folder
5. java -jar target/todo-0.0.1-SNAPSHOT.jar
6. Verify your RESTful calls.
7.To run the multiple instances of the same service on the same machine using different ports, you might need to pass the spring.datasource.url as well, since I am using in memory h2 DB, sharing the same DB for multiple instances  might cause a problem. So for testing only.

java -Dlog.location=C:/temp/log -jar target/todo-0.0.1-SNAPSHOT.jar --server.port=9001 --spring.datasource.url=jdbc:h2:file:~/test3;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE

and 

java -Dlog.location=C:/temp/log -jar target/todo-0.0.1-SNAPSHOT.jar --server.port=9002 --spring.datasource.url=jdbc:h2:file:~/test4;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE


