FROM openjdk:8-jdk-alpine

EXPOSE 8080

ARG JAR_FILE=build/libs/gongbaek.v1.backend-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /gongbaek_app.jar

ENTRYPOINT ["java", "-jar", "/gongbaek_app.jar"]