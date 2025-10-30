# ==== Build stage ====
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the JAR (skip tests for faster builds)
RUN mvn clean package -DskipTests

# ==== Run stage ====
FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY --from=build /app/target/LibraryProject2-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 5000
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
