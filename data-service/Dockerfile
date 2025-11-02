# Use the official OpenJDK image as a parent image
FROM eclipse-temurin:21-jdk

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper and pom.xml file
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY data-service/pom.xml data-service/

# Make the Maven wrapper executable
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline -pl data-service

# Copy the source code
COPY data-service/src data-service/src

# Build the application
RUN ./mvnw package -DskipTests -pl data-service

# Expose the port the app runs on
EXPOSE 8081

# Run the application
CMD ["java", "-jar", "data-service/target/data-service-0.0.1-SNAPSHOT.jar"]