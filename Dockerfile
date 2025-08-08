# ==============================
# Stage 1: Build the application
# ==============================
FROM eclipse-temurin:23-jdk-alpine AS builder

# Set working directory
WORKDIR /app

# Copy Maven wrapper & settings
COPY mvnw .
COPY .mvn .mvn

# Make mvnw executable
RUN chmod +x mvnw

# Copy pom.xml first to cache dependencies
COPY pom.xml .

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy the rest of the project
COPY src ./src

# Build the jar (skip tests for speed)
RUN ./mvnw clean package -DskipTests

# ==============================
# Stage 2: Run the application
# ==============================
FROM eclipse-temurin:23-jre-alpine

# Set working directory
WORKDIR /app

# Copy jar from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose application port
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
