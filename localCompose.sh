
#/bin/sh

./mvnw install

docker build -t  sbposts .

docker compose down

docker compose up