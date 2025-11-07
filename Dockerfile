FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app
COPY . /app

# Build the shaded jar
RUN apt-get update && apt-get install -y maven && mvn clean package -DskipTests

EXPOSE 4567

# ✅ Run the shaded jar (includes Gson & Spark)
CMD ["sh", "-c", "java -jar target/SecurePasswordManager-1.0.0-shaded.jar"]
