FROM openjdk:17-alpine

LABEL authors="Natan Oliveira"

COPY target/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]