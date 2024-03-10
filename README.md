# Student Result Management System

This application offers a
comprehensive solution for educational institutions to add and manage students, courses, and their results efficiently.
Built using Java and Spring Boot, it leverages Maven for dependency management, Docker for containerization, MySQL for persistent storage, Flyway for database migration, and Swagger for API documentation.

### Structure

> Check Here ---> [https://tree.nathanfriend.io/](https://tree.nathanfriend.io/) <---

```text
.
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── io.shyftlabs.srms/
│   │   │       ├── controller
│   │   │       ├── dto
│   │   │       ├── error
│   │   │       ├── repository
│   │   │       ├── service
│   │   │       ├── validation
│   │   │       └── SRMSApplication.java
│   │   └── resources/
│   │       ├── db.migration
│   │       │    └── V1__Initial_schema.sql
│   │       └── application.yml
│   └── test/
│       ├── java/
│       │   └── io.shyftlabs.srms/
│       │       └── AbstractTest.java
│       └── resources/
│           └── application.yml
├── .gitignore
├── .env
├── compose.yml
└── pom.xml
```

### Requirements

To run this application, you will need the following installed on your machine:

- **JDK 17**: The Java Development Kit is a software development environment used for developing Java applications.
- **Maven**: A build automation tool used primarily for Java projects.
- **Docker**: A platform used to develop, ship, and run applications inside containers.
- **MySQL**: An open-source relational database management system.

### Setup

Follow these steps to set up and run the application:

1. Clone the project to your local machine.
2. Open a terminal and navigate to the project folder.
3. Run the command `docker compose up -d` to start the Docker containers for MySQL.
4. Run the command `mvn spring-boot:run` to start the application.

### Features

The Student Result Management System offers the following features:

- **Student Management**: Allows you to add new students with validations for email and age, view all students, and delete students.
- **Course Management**: Provides the ability to add new courses, view all courses, and delete courses.
- **Result Management**: Enables you to associate students with courses and assign scores, view all results, with automatic updates reflecting deletions in students or courses.
- **API Documentation**: Offers comprehensive API documentation using Swagger.

### APIs

You can access the API documentation at the following URL when the application is running:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

This will provide you with a list of all available API endpoints, along with descriptions and examples of how to use them.


## Running JUnit Tests

JUnit is a simple framework to write repeatable tests in Java. This project uses JUnit for unit testing of the application.

To run the JUnit tests, follow these steps:

1. Ensure that you have Maven installed on your machine. If not, you can download and install Maven from [here](https://maven.apache.org/download.cgi).

2. Open a terminal and navigate to the root directory of the project.

3. Run the following command:

    ```bash
    mvn test
    ```

This command will start the execution of the tests. The results will be displayed in the terminal once the tests have completed.

Please note that the tests may take a few minutes to complete, depending on the number and complexity of the tests.

## Deployment to Production

This section provides instructions on how to build and run a Docker image of the application for deployment to a production environment.

### Building the Docker Image

1. Ensure Docker is installed on your machine. If not, you can download and install Docker from [here](https://www.docker.com/products/docker-desktop).

2. Navigate to the root directory of the project in your terminal.

3. Build the Docker image using the following command:

    ```bash
    mvn clean
    mvn package 
    docker build -t student-result-management-system:1.0.0 .
    ```

### Running the Docker Image in Production

To run the Docker image in a production environment using Docker Compose, follow these steps:

1. Ensure Docker is installed on your production server. If not, you can download and install Docker from [here](https://www.docker.com/products/docker-desktop).

2. Navigate to the `production` directory in your terminal. This directory should contain your `docker-compose.yml` file.

    ```bash
    cd production
    ```

3. Run the Docker Compose file using the following command:

    ```bash
    docker-compose up -d
    ```

   This command will start all services defined in your `docker-compose.yml` file in detached mode (`-d`).

Please note that your `docker-compose.yml` file should be set up to run your application and any other services (like a database) that your application depends on. Also, ensure that all the necessary environment variables are correctly set in a `.env` file or directly in the `docker-compose.yml` file.
