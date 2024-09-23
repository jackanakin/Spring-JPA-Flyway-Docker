## Spring Monolith Project

<div align="center">

[![CircleCI](https://dl.circleci.com/status-badge/img/gh/jackanakin/spring-monolith-project/tree/main.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/jackanakin/spring-monolith-project/tree/main)
[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
<br/>
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=jackanakin_spring-monolith-project&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=jackanakin_spring-monolith-project)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=jackanakin_spring-monolith-project&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=jackanakin_spring-monolith-project)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=jackanakin_spring-monolith-project&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=jackanakin_spring-monolith-project)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=jackanakin_spring-monolith-project&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=jackanakin_spring-monolith-project)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=jackanakin_spring-monolith-project&metric=coverage)](https://sonarcloud.io/summary/new_code?id=jackanakin_spring-monolith-project)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=jackanakin_spring-monolith-project&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=jackanakin_spring-monolith-project)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=jackanakin_spring-monolith-project&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=jackanakin_spring-monolith-project)

</div>

### 1. Building:
Build JAVA project:
>./mvnw clean install

Build APP Docker image (modify 'jackanakin' with your account':
>docker build -f docker/app/Dockerfile --build-arg JAR_FILE=target/spring-boot-0.0.1-SNAPSHOT.jar -t jackanakin/spring_app .

>docker push jackanakin/spring_app

Build Proxy Docker image (modify 'jackanakin' with your account':
>docker build -f docker/proxy/Dockerfile --build-arg CONF_FILE=docker/proxy/default.conf -t jackanakin/spring_proxy .

>docker push jackanakin/spring_proxy

### 2. Deploying:
Create docker network (to allow containers to communicate with each other):
>docker network create spring_network

Run database container:
>docker run -d --net spring_network --name springdb -e POSTGRES_USER=springboot -e POSTGRES_DB=springboot -e POSTGRES_PASSWORD=springbootpasswd postgres

Run application container 
>docker run -d --net spring_network --name springapp jackanakin/spring_app --datasource_url=springdb:5432/springboot

Run proxy container:
>docker run --net spring_network -d -p 80:80 --name springproxy jackanakin/spring_proxy

Go to http://localhost:80/person to list all persons from database

### 3. Developing:

1. Create a database:
> docker run -d --name springdb -p 5432:5432 -e POSTGRES_USER=springboot -e POSTGRES_DB=springboot -e POSTGRES_PASSWORD=springbootpasswd postgres

> check "application.properties" for custom settings

2. Git clone or Use this template.

3. Run

Project structure:
```
->  docker: docker deploy related code
->  .circleci: Circle CI configuration
->  src/
->      test/java/br/kuhn/dev/springboot: unit tests
->      main/java/br/kuhn/dev/springboot: main code
->        _common: common classes and utilities like BaseEntity, BaseRepository and etc.
->        _core:   main functionalities like AAA, logging, exception handling, and etc.
->        foo:     domain related code

->  src/main/resources
->      application.properties: configurations like database credentials, flyway settings, server port and etc.
->      db/migration: flyway .sql migrations
```

| Have |  |
| ------------- | ------------- |
| Spring Security | Customized [UserDetails](https://github.com/jackanakin/spring-monolith-project/blob/main/src/main/java/br/kuhn/dev/springboot/_core/user/entity/User.java), [Authorization](https://github.com/jackanakin/spring-monolith-project/blob/main/src/main/java/br/kuhn/dev/springboot/_core/security/config/SecurityConfig.java) with springWebFilterChain and JwtToken |
| Spring JPA  | [Base Repository](https://github.com/jackanakin/spring-monolith-project/blob/main/src/main/java/br/kuhn/dev/springboot/_common/repository/BaseRepository.java) and [Custom Repository](https://github.com/jackanakin/spring-monolith-project/blob/main/src/main/java/br/kuhn/dev/springboot/_common/repository/CustomRepository.java) for pagination  |
| Flyway  | Optional for [database migrations](https://github.com/jackanakin/spring-monolith-project/tree/main/src/main/resources/db/migration)  |
| Lombok  | Eliminate boilerplate code in entities and dtos with ctor, setters and gettes  |
| MapStruct  | [Entity <--> Dto conversion](https://github.com/jackanakin/spring-monolith-project/blob/main/src/main/java/br/kuhn/dev/springboot/foo/mapper/FooMapper.java)  |
| Bean validation  | [Javax Persistence](https://github.com/jackanakin/spring-monolith-project/blob/main/src/main/java/br/kuhn/dev/springboot/foo/entity/Foo.java) for validating incoming dto's  |
| RestControllerAdvice  | Intercept exceptions and customize the response with [@ExceptionHandler](https://github.com/jackanakin/spring-monolith-project/blob/main/src/main/java/br/kuhn/dev/springboot/_core/validation/handler/ValidationExceptionHandler.java)  |
| Docker  | [Simple docker sample](https://github.com/jackanakin/spring-monolith-project/tree/main/docker)  |
| Aspect Oriented Programming  | [Intercept](https://github.com/jackanakin/spring-monolith-project/blob/main/src/main/java/br/kuhn/dev/springboot/_core/logger/ControllerLogger.java) before and after methods execution of classes  |
| Base Classes  | [Base classes](https://github.com/jackanakin/spring-monolith-project/tree/main/src/main/java/br/kuhn/dev/springboot/_common) which aggregate common behaviors between controllers, entities and etc. |
| Swagger  | Run the application and go to http://localhost:8080/swagger-ui/index.html  |
| JUnit5 and Mockito  | [Unit test for endpoint controllers](https://github.com/jackanakin/spring-monolith-project/blob/main/src/test/java/br/kuhn/dev/springboot/foo/controller/FooControllerTest.java)  |
| JaCoCo  | Test [coverage report]() with JaCoCo  |
| Insomnia  | [Practical documentation for development](https://github.com/jackanakin/spring-monolith-project/blob/main/insomnia.json)  |
| CI Build  | [CircleCI](https://app.circleci.com/pipelines/github/jackanakin/spring-monolith-project) for continuous integration and test execution |
| Code quality  | [SonarCloud](https://sonarcloud.io/project/overview?id=jackanakin_spring-monolith-project) for continuous code quality analysis  |

| To do |  |
| ------------- | ------------- |
| API Versioning | |
| Throttler | |
| Telemetry/Monitoring | ELK stack ? |
| oAuth + keycloak | from my other repo [here](https://github.com/jackanakin/OAuth-2.0-in-Spring-Boot-Applications) |
<!--
TODO
rest-template for integration test
distributed tracing
spring cloud + eureka + gateway
oauth2 + keycloak
sagas
JMS
i18n
Spring cloud microservice
Session management or keycloak
image;files upload/download
kafka
aws
mongodb
password validator bean
password hook no user save
-->
