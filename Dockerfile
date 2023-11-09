FROM eclipse-temurin:17-jdk-alpine

ARG JAR_FILE=target/sbpostit-*.jar

ENV spring.datasource.url=jdbc:mariadb://192.168.1.116:3306/sbpostit

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","./app.jar"]