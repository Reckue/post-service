# post-api
**v.1.0.4**

Service for publications, tutorials and articles.

![Deploy](https://github.com/Reckue/post-api/workflows/deploy/badge.svg)
[![Slack](https://img.shields.io/badge/Slack-Join-green.svg?style=flat-circle&colorB=red)](https://reckue.slack.com)

## Stack
- Open JDK 11
- Gradle 5.2.1
- Spring Boot 2.2.2
- Spring Data 2.2.2
- Spring Security 2.2.2
- Spring Cloud 2.2.2
- Netflix Eureka 2.2.4
- Netflix Zuul 2.2.4
- MongoDB 4.2
- Lombok 1.18.12
- Model Mapper 2.3.7
- Mongobee 0.13
- Swagger 2.9.2
- Open API 3.0.0
- Junit 5
- Checkstyle 7.2
- Openshift CLI 4
- GitHub Workflow CI

## Run with terminal
To run your application without first building an archive use the bootRun task:
```
gradle bootRun
```

#### 1. Build project
To build use the command:
```
gradle build
```
#### 2. Run the application
To create a jar file write following command and put enter:
```
java -jar ./build/libs/post-api-1.0.2.jar
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

#### 4. Run MongoDB image
Run the MongoDB latest version image with 27017 port:
```
docker run -p 27017:27017 -t mongo:latest
```

#### 5. Run application image
Run this image in a container on the 9002 port and connect with the `develop` spring profile on the 8080 port:
```
docker run -e "--spring.profiles.active=develop" -p 8080:9002 -t reckue/post
```

#### 6. Demonstration
Open the http://localhost:8080 on your browser.


## Contribute
For any problems, comments, or feedback please create an issue [here on GitHub](https://github.com/Reckue/post-api/issues).
<br>


## License
Reckue is released under the [MIT license](https://en.wikipedia.org/wiki/MIT_License).
