#FROM downloads image from DockerHub (hub.docker.com); war vorher:adoptopenjdk/openjdk11:latest
FROM adoptopenjdk/openjdk11:latest
ARG JAR_FILE=target/*.jar
EXPOSE 8081
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]