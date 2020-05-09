FROM openjdk:11-jdk-slim

COPY ./target/swarm-0.0.1-SNAPSHOT.jar /app/app.jar

RUN apt-get update && apt-get --assume-yes install curl

ENTRYPOINT ["java", "-jar", "/app/app.jar"]

