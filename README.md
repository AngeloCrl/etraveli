# etraveli-assignment
![](https://img.shields.io/badge/build-success-brightgreen.svg)

# Stack
![](https://img.shields.io/badge/java_17-✓-blue.svg)
![](https://img.shields.io/badge/spring_boot_3.3.4-✓-blue.svg)
![](https://img.shields.io/badge/mysql_8.0.39-✓-blue.svg)

[//]: # (![]&#40;https://img.shields.io/badge/liquibase_4.9.1-✓-blue.svg&#41;)
![](https://img.shields.io/badge/openapi_2.0.2-✓-blue.svg)
![](https://img.shields.io/badge/maven-✓-blue.svg)
![](https://img.shields.io/badge/docker-✓-blue.svg)


# How to use this code?
1. Make sure you have [MySql 8.0.39](https://dev.mysql.com/downloads/windows/installer/) installed within your local machine
2. Create a new database with the following credentials:
    - `name: db`
    - `username: root`
    - `password: root`
    - `host: localhost:3306`
3. Open a cmd and navigate into a folder of your choice within your local machine
4. Clone this repository into this folder using the following command:
```
PS > git clone https://github.com/AngeloCrl/etraveli.git
```
5. Open the project with the Intellij IDEA
6. In the top-right corner of IntelliJ's window, use the `install` command at maven's toolbar,
   in order for the jar-file to be created: `Maven > Lifecycle > install`
7. Add a new Run Configuration and set: `Java 17`, `Maven wrapper` and the `spring-boot:run` command
8. Run the program by clicking the run-button or by pressing Shift+F10
9. Once the program starts, navigate to `http://localhost:8080/swagger-ui/index.html#/` in your browser to check everything is working correctly
10. You are ready to start sending requests.

# How to run with Docker
**No Database needs to be pre-installed in your local machine.**
**Instead, you need to have [Docker](https://docs.docker.com/get-docker/) installed!**

1. Open a cmd and navigate into a folder of your choice
2. Clone this repository into this folder using the following command:
```
PS > git clone https://github.com/AngeloCrl/etraveli.git
```
3. Open the project with the Intellij IDEA
4. Within the `application.properties` file:
    - un-comment the line 3: `spring.datasource.url=jdbc:mysql://db:3306/etraveli`
    - comment-out the line 4: `spring.datasource.url=jdbc:mysql://localhost:3306/etraveli?serverTimezone=UTC`
5. In the top-right corner of IntelliJ's window, use the `install` command at maven's toolbar,
   in order for the jar-file to be created: `Maven > Lifecycle > install`
6. Open the Intellij's terminal and run the following command:
```
PS > docker-compose up
```
7. Once docker is done creating the images and their respective containers, you may open your browser and navigate to: `http://localhost:8080/swagger-ui/index.html#/`
8. You are ready to start sending requests