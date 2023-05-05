## Spring Boot + REST + CLI Arguments + JPA + Flyway + Nginx + Docker

[![CircleCI](https://dl.circleci.com/status-badge/img/gh/jackanakin/Spring-JPA-Flyway-Nginx-Docker/tree/main.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/jackanakin/Spring-JPA-Flyway-Nginx-Docker/tree/main)
[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=jackanakin_Spring-JPA-Flyway-Nginx-Docker)](https://sonarcloud.io/summary/new_code?id=jackanakin_Spring-JPA-Flyway-Nginx-Docker)

Run all commands on root directory<br/>
Just do step 2 to run, if you want to build run step 1
<br/>
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

Project structure:
->  docker: docker deploy related code

->  src/main/java/br/kuhn/dev/springboot
->      _common: common classes and utilities like BaseEntity, BaseRepository and etc.
->      _core:   main functionalities like AAA, logging, exception handling, and etc.
->      foo:     domain related code

->  src/main/resources
->      application.properties: configurations like database credentials, flyway settings, server port and etc.
->      db/migration: flyway .sql migrations


Have:

Spring Security with customized User via UserDetails and Authorization filter with JwtToken
JPA + Flyway (optional) + Lombok + MapStruct + Bean validation
ControllerAdvice for Rest endpoints exception handling
Spring CommandLineRunner for initialization operations
Docker and external properties with application.properties
Aspect Oriented Programming for custom operations
Base classes to reduce boiler plate like BaseEntity, BaseDto, BaseRepository, BaseService and BaseController


TODO

Swagger
tests
api-versioning
brute-force
rest-template for integration test
unit testing
logging with ELK Stack
distributed tracing
spring cloud + eureka + gateway
oauth2 + keycloak
sagas
JMS
i18n
Insomnia or Postman
Spring cloud microservice
CI Build
PageImpl
Session management or keycloak
hot reload
image;files upload/download
kafka
aws
mongodb
user registration
password validator bean
password hook no user save
rever exceptions (mensagens dentro)