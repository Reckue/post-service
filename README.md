# post-api

<img src="https://github.com/reckue/post-api/workflows/pipelines/badge.svg?branch=develop"><br>

## Stacks
- OpenJDK 11
- Spring Boot
- Gradle
- Travis CI
- Swagger
- MongoDB
- Mongobee
- Spring Security
- Spring Data
- Lombok
- Junit 5
- DevTools
- ModelMapper
- Checkstyle

## Run with terminal
```
    gradle bootRun
```

```
    gradle build
```

```
- java -jar 1.0.SNAPSHOT.jar
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
