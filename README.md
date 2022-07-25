## Spring Boot + REST + CLI Arguments + JPA + Flyway + Docker
Build project:
>./mvnw clean install

1. Build Docker image or pull from repo:
>docker build --build-arg JAR_FILE=target/spring-boot-0.0.1-SNAPSHOT.jar -t jackanakin/springboot .

>docker pull jackanakin/springboot

2. Create docker network (to allow both containers to communicate with each other):
>docker network create spring_network

3. Run database container:
>docker run -d -it --net spring_network --name springboot_db -e POSTGRES_USER=springboot -e POSTGRES_DB=springboot -e POSTGRES_PASSWORD=springbootpasswd -p 5432:5432 postgres

Wait 15 seconds to ensure database is accepting connections
 
>docker run -d -it --net spring_network -p 8080:8080 --name spring jackanakin/springboot --app_name="Docker Yeah" --datasource_url=springboot_db:5432/springboot

Go to http://localhost:8080/person to list all persons from database
