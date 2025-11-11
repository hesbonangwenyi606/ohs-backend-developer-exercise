# Use Java 21 JDK
FROM eclipse-temurin:21-jdk-jammy

# Install Maven
RUN apt-get update && apt-get install -y maven

# Set working directory
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src src

# Build the project using system Maven
RUN mvn clean package -DskipTests

# Expose port
EXPOSE 8080

# Run the Spring Boot JAR
ENTRYPOINT ["java","-jar","target/ohs-backend-developer-exercise-0.0.1-SNAPSHOT.jar"]
