## Spring Boot + REST + CLI Arguments + JPA + Flyway + Nginx + Docker
Run all commands on root directory<br/>
Just do step 2 to run, if you want to build run step 1
<br/>
### 1. Building:
Build JAVA project:
>./mvnw clean install

Build APP Docker image (modify 'jackanakin' with your account':
>docker build -f docker/app/Dockerfile --build-arg JAR_FILE=target/spring-boot-0.0.1-SNAPSHOT.jar -t jackanakin/spring_app .
<br/>
>docker push jackanakin/spring_app

Build Proxy Docker image (modify 'jackanakin' with your account':
>docker build -f docker/proxy/Dockerfile --build-arg CONF_FILE=docker/proxy/default.conf -t jackanakin/spring_proxy .
<br/>
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
