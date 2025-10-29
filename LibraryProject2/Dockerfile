# Use OpenJDK 17 as base image
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build jar
RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy jar from previous stage
COPY --from=build /app/target/LibraryProject2-0.0.1-SNAPSHOT.jar app.jar

# Expose port 5000
EXPOSE 5000

# Run app
ENTRYPOINT ["java","-jar","/app/app.jar"]
