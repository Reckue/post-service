# post-api

It's a service for posting articles about different programming languages:

- JAVA;
- PYTHON;
- JAVASCRIPT;
- C;
- KOTLIN;
- BASH;
- BAT. 

Post consists from nodes having different types:
- text;
- image;
- video;
- code.

As well as post has tag. It would be filtered and sorted by tag.

Post has type describing status of activity:
- active;
- deleted;
- banned;
- moderated.

# Сustomization of Docker
## Step 1
Install docker.

## Step 2
Add Dockerfile to root directory:
```java
# Dockerfile
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
Run this image in a container:
```
docker run -p 8080:9002 -t reckue/post
```






