#!/bin/bash

docker tag reckue/post:latest reckue/post:latest
docker build -t reckue/post:latest .
