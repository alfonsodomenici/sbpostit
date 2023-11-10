
#/bin/sh

./mvnw install

docker build -t  sbposts .

docker image rm dhtssdev/sbposts

docker image tag sbposts dhtssdev/sbposts

docker login 

docker push dhtssdev/sbposts
