#/bin/sh

./mvnw install

docker build -t  sbposts .

docker rm -f sbposts

docker run -d --name sbposts -p8080:8080 sbposts

docker logs -f sbposts