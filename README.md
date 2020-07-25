# post-api
**v.1.0.2.RELEASE**

Service for publications, tutorials and articles.

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

#### 1. Build project
To build use the command:
```
gradle build
```
#### 2. Run the application
To create a jar file write following command and put enter:
```
java -jar ./build/libs/RELEASE-1.0.1.jar
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
