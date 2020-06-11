# post-api

![deploy](https://github.com/Reckue/post-api/workflows/deploy/badge.svg)


## Stacks
- JDK 11
- Gradle
- Spring Boot
- Spring Data
- Spring Security
- Spring Cloud
- MongoDB
- Lombok
- ModelMapper
- Mongobee
- Swagger
- Junit 5
- Mockito
- DevTools
- GitHub Workflow
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

Create a jar file:
```
java -jar RELEASE-1.0.0.jar
```

## Run with docker
You need an installed docker program on your computer, or download it, such as:
```
apt-get install docker
```

#### 1. Install Gradle
Build project with Gradle:
```
apt-get install gradle
```

#### 2. Build project
Build project with Gradle:
```
gradle build
```

#### 3. Create an image
After build, please create an image woth and link tag with `-t` parameter:
```
docker build -t reckue/post .
```

#### 4. Run the image
Run this image in a container on the 9002 port and connect with 8080 port there:
```
docker run -p 8080:9002 -t reckue/post
```

#### 5. Demonstration
Open the http://localhost:8080 on your browser.
