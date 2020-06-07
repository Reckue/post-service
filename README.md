# post-api

It's a service for posting articles about different programming languages:

- JAVA;
- PYTHON;
- JAVASCRIPT;
- C;
- KOTLIN;
- BASH;
- BAT. 

Stacks that were used:
- Spring Boot;
- Gradle;
- Travis CI;
- Swagger;
- MongoDB;
- Mongobee;
- Spring Security;
- Spring Data;
- Lombok;
- JUnit;
- DevTools;
- ModelMapper;
- Checkstyle;



For use:
- gradle bootRun
- gradle build
- java -jar 1.0.SNAPSHOT.jar


# customization of docker
## Step 1
Install docker.

## Step 2
Add Dockerfile to root directory:
```java
FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

## Step 3
Build and create an image:
```
docker build -t reckue/post .
```

## Step 4
Run this image in the container:
```
docker run -p 8080:9002 -t reckue/post
```

## Step 5
Run with a spring profile image in the container:
```
docker run -e "--spring.profiles.active=develop" -p 8080:9002 -t reckue/post
```



