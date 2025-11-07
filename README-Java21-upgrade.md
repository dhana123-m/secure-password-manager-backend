# Java 21 Upgrade Notes

This project was updated to target Java 21 (LTS).

What changed:
- `pom.xml` now sets `java.version` to `21` and configures `maven-compiler-plugin` to compile with Java 21.
- `Dockerfile` base image changed to `eclipse-temurin:21-jdk` so container runtime uses Java 21.

Local setup / build
1. Install a Java 21 JDK on your machine (Eclipse Temurin, Adoptium, or OpenJDK 21).
   - Verify with: `java -version`
2. Ensure Maven is installed and on your PATH.
   - Verify with: `mvn -v`
3. From the `backend` folder, build the project:

```powershell
cd backend
mvn clean package
```

4. Run the packaged app (if applicable):

```powershell
# Adjust the main class as needed; project uses simple classpath layout
java -cp "target/classes;target/dependency/*" PasswordAPI
```

Docker build
1. Build the container (from repository root):

```powershell
cd backend
docker build -t securepm-backend:java21 .
```

Notes and follow-ups
- The automated GitHub Copilot "upgrade" tool was not available for this environment, so changes were applied manually.
- After building with Java 21, run the app and tests. If compilation or runtime errors occur, capture the errors and we can address them iteratively (common issues: removed/changed APIs, module system differences, third-party library compatibility).
- If you want me to try an automated upgrade using the Copilot upgrade tools, please ensure your account has the required Copilot plan access.
