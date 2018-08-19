# JMS test project

Web-client app gets requests and sends them via ActiveMQ to the server app that processes requests, works with db and sends back responses.

Stack of technologies:
* Java
* Spring Framework
* Apache ActiveMQ
* PostgreSQL
* HTML/CSS/JS
* Maven
* Tomcat

How to run:

1. It's assumed that you are in project root now + Apache Tomcat running on 8080 port
2. Specify your ActiveMQ path, PostgreSQL path and credentials in:
```shell
webclient\src\main\resources\application.yml
spring_server\src\main\resources\application.yml
spring_server\src\main\resources\application.properties
```
3. Install project library into local maven repository:
```shell
cd webclient-server-library
mvn clean install
```
4. Get server app package:
```shell
cd ../spring_server
mvn clean package
```
5. Get web-client app package:
```shell
cd ../webclient
mvn clean package
```
6. Take server and web-client packages(*.war) from their target/ directories and place it in Tomcat /webapps directory. 
7. From now, web-client view available at: ```http://localhost:8080/web-client```