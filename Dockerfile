#FROM openjdk:13-alpine
FROM adoptopenjdk/openjdk14
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","--enable-preview","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
