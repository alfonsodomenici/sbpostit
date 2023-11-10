FROM eclipse-temurin:17-jdk-alpine

ARG JAR_FILE=target/sbpostit-*.jar

RUN addgroup -S app && adduser -S app -G app

USER app

ENV SPRING.DATASOURCE.URL=jdbc:mariadb://192.168.1.116:3306/sbpostit
ENV SPRING.DATASOURCE.USERNAME=postit
ENV SPRING.DATASOURCE.PASSWORD=postit


COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","./app.jar"]