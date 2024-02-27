FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/generate-invoice-0.0.1-SNAPSHOT.jar /app/generate-invoice-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "generate-invoice-0.0.1-SNAPSHOT.jar"]
