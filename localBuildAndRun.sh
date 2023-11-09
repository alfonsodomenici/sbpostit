#/bin/sh

docker build -t  sbposts .

docker rm -f sbposts

docker run -d --name sbposts --network apps -p8080:8080 sbposts