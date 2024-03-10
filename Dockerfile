# Start with a base image containing Java runtime (Here we are using OpenJDK)
FROM openjdk:17-jdk

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/student-result-management-system-1.0.0.jar

# Add the application's jar to the container
ADD ${JAR_FILE} application.jar

# Run the jar file
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/application.jar"]