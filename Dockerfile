# Step 1: Use a JDK base image
FROM eclipse-temurin:23-jdk-alpine

# Step 2: Set a working directory
WORKDIR /app

# Step 3: Copy Maven/Gradle build file and download dependencies
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline -B

# Step 4: Copy the source code
COPY src ./src

# Step 5: Build the Spring Boot application
RUN ./mvnw package -DskipTests

# Step 6: Run the application
# Replace 'WeatherAppBasicApplication.jar' with the actual jar name in target/
CMD ["java", "-jar", "target/WeatherAppBasicApplication-0.0.1-SNAPSHOT.jar"]
