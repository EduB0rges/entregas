FROM openjdk:21-jdk-slim-buster

WORKDIR /entregas

COPY target/entregas-0.0.1-SNAPSHOT.jar /entregas/entregas.jar

EXPOSE 8080

CMD ["java", "-jar", "entregas.jar"]