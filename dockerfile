# 1. Build stage
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN mvn dependency:go-offline

COPY src src
RUN mvn package -DskipTests

# 2. Run stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Se você configurou porta diferente, exponha aqui
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
