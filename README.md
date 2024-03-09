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

### Features

- **Student Management**: Add new students with validations for email and age, view all students, and delete students.
- **Course Management**: Ability to add new courses, view all courses, and delete courses.
- **Result Management**: Associate students with courses and assign scores, view all results, with automatic updates reflecting deletions in students or courses.
- **API Documentation**: Comprehensive API documentation using Swagger.

### Requirements

- JDK 17
- Maven 
- Docker 
- MySQL 
- Redis 

### Setup

- Clone the project
- Open terminal in the project folder
- Run `docker compose up -d`
- Run `mvn spring-boot:run`

### APIs

Swagger: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
