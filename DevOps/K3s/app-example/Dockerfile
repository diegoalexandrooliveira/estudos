FROM openjdk:11-jdk-slim

COPY ./target/app_example-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]