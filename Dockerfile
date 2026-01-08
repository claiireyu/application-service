# Stage 1: Build stage
# Explicitly specify linux/amd64 platform for Fargate compatibility
FROM --platform=linux/amd64 maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies (this layer will be cached if pom.xml doesn't change)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests -B

# Stage 2: Runtime stage
# Explicitly specify linux/amd64 platform for Fargate compatibility
FROM --platform=linux/amd64 eclipse-temurin:17-jre-jammy
WORKDIR /app

# Install curl for healthcheck
RUN apt-get update && apt-get install -y --no-install-recommends curl && \
    rm -rf /var/lib/apt/lists/*

# Create a non-root user for security
RUN groupadd -r spring && useradd -r -g spring spring

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Change ownership of the JAR file to the spring user
RUN chown spring:spring app.jar

# Switch to non-root user
USER spring:spring

# Expose the application port (default Spring Boot port)
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

