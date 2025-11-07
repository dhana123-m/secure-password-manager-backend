FROM eclipse-temurin:21-jdk

WORKDIR /app
COPY . /app
RUN apt-get update && apt-get install -y maven && mvn clean package -DskipTests

CMD ["java", "-cp", "target/classes:target/dependency/*", "PasswordAPI"]
