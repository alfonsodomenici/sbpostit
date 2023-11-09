FROM eclipse-temurin:17-jdk-alpine

ARG JAR_FILE=target/sbpostit-*.jar

ENV spring.datasource.url=jdbc:mariadb://192.168.1.116:3306/sbpostit

ENV spring.security.oauth2.client.provider.keycloak.issuer-uri=http://192.168.1.116:8081/auth/realms/sbrealm

ENV spring.security.oauth2.resourceserver.jwt.issuer-uri=http://192.168.1.116:8081/auth/realms/sbrealm


COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","./app.jar"]