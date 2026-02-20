# Spring Tutorial Project Context

This project is a comprehensive collection of tutorials and examples for learning the **Spring Framework** and **Spring Boot**. It is structured as a multi-module Maven project, progressing from core Spring concepts to advanced Spring Boot features.

## Project Overview

- **Main Technologies:** Java 21 (with some modules on 17), Spring Framework 6+, Spring Boot 3.2.x, Maven.
- **Data Access:** H2 (In-memory/Runtime), Spring Data JPA, Spring JDBC Template.
- **Architecture:** Monolithic multi-module aggregator.
- **Additional Libraries:** Lombok, Swagger/OpenAPI (SpringDoc), JavaFaker (for data generation), ModelMapper, Spring Boot Actuator.

## Module Breakdown

### 1. `spring-Basic`
Focuses on core Spring Framework concepts without Spring Boot.
- **Topics:** Dependency Injection (DI), Inversion of Control (IoC), Bean Lifecycle, Bean Scopes, Tight vs. Loose Coupling, Annotation vs. Java-based Configuration, StereoType Annotations (`@Component`, `@Service`, etc.).
- **Java Version:** 21.

### 2. `springBootBasic`
Introduction to Spring Boot.
- **Topics:** Spring Boot Starters, Web MVC, Configuration Management (Profiles: `dev`, `prod`), Actuator for monitoring, Swagger integration for API documentation.
- **Java Version:** 17.

### 3. `springBootJPA`
Working with databases using Spring Data JPA.
- **Topics:** JPA Entities, Repositories, Relationships, Data Mapping (ModelMapper), Bean Validation, Data Seeding (JavaFaker).
- **Database:** H2 (default), with placeholders for MySQL.
- **Java Version:** 21.

### 4. `springJdbcTemplate`
Direct JDBC interaction using Spring's `JdbcTemplate`.
- **Topics:** CRUD operations, Complex Join queries, Schema management (`schema.sql`, `data.sql`).
- **Java Version:** 21.

## Building and Running

### Prerequisites
- JDK 21 (required for root and most modules).
- Maven 3.x.

### Commands
- **Build all modules:**
  ```bash
  mvn clean install
  ```
- **Run a Spring Boot module:**
  ```bash
  # Example for springBootBasic
  mvn spring-boot:run -pl springBootBasic
  ```
- **Run Tests:**
  ```bash
  mvn test
  ```
  *Note: `springJdbcTemplate` has specific test profiles for H2 vs. actual databases mentioned in its README.*

## Development Conventions

- **Package Root:** `com.saha.amit`
- **Database:** Default development is done using H2 in-memory database.
- **Configuration:** 
  - Uses `application.properties` and `application.yml`.
  - Profile-based configuration is active (check `spring.profiles.active` in `application.yml`).
- **Boilerplate:** Heavy use of **Lombok** (`@Data`, `@NoArgsConstructor`, etc.). Ensure your IDE has the Lombok plugin installed.
- **API Documentation:** Swagger UI is available at `/swagger-ui/index.html` (configured via `springdoc-openapi`).
- **Testing:** 
  - Integration tests often use `@SpringBootTest`.
  - `springJdbcTemplate` uses `@JdbcTest` for repository-level testing.

## Key Files
- `pom.xml`: Root Maven configuration.
- `SpringBoot.docx`: Likely contains theoretical notes on Spring Boot.
- `ToDoList.sh`: Contains some IDE shortcuts and minor tasks.
- `spring-Basic/src/main/java/com/saha/amit/`: Contains progressive tutorial packages (a_tightCoupeling to i_lazyBeanLoad).
