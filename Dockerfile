# Use official Java 17 image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy the Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src
COPY frontend frontend

# Build the app (this will also bundle your frontend build if copied into resources/static)
RUN ./mvnw clean package -DskipTests

# Run the Spring Boot jar
CMD ["java", "-jar", "target/portfolio-0.0.1-SNAPSHOT.jar"]
