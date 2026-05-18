# ── Stage 1: Build ──────────────────────────────────────────────────
FROM maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /app

# Copy pom.xml first so Docker can cache the dependency layer.
# Maven only re-downloads deps when pom.xml actually changes.
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Now copy source and compile
COPY src ./src
RUN mvn clean package -DskipTests -B

# ── Stage 2: Runtime ────────────────────────────────────────────────
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
