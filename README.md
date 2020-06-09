# post-api

<img src="https://github.com/reckue/post-api/workflows/pipelines/badge.svg?branch=develop"><br>

## Stacks
- OpenJDK 11
- Spring Boot
- Spring Data
- Spring Security
- MongoDB
- Gradle
- Lombok
- ModelMapper
- Mongobee
- Swagger
- Junit 5
- DevTools
- Travis CI
- Checkstyle

## Run with terminal

 To run your application without first building an archive use the bootRun task:
```
gradle bootRun
```

 To build use the command:
```
gradle build
```

 Create jar file:
```
    java -jar 1.0.SNAPSHOT.jar
```

## Run with docker
Install docker.

### Create image
Build and create an image:
```
docker build -t reckue/post .
```

### Run image
Run this image in the container:
```
docker run -p 8080:9002 -t reckue/post
```
