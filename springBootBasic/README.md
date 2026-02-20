# Spring Boot Basic: Introduction to Spring Boot

This module introduces **Spring Boot**, focusing on auto-configuration, web development (MVC), and externalized configuration management.

## Key Features
- **Auto-Configuration:** No more manual XML or complex Java config for common tasks.
- **Spring Boot Starters:** Pre-configured dependencies like `spring-boot-starter-web`.
- **Profiles:** Environment-specific configurations (Dev vs Prod).
- **Validation:** Using JSR-380 (`jakarta.validation`) for API request bodies.
- **Swagger/OpenAPI:** Automatic API documentation generation.

---

## Endpoint Documentation

### 1. Student Management (Web MVC)
**Endpoint:** `GET /api/v1/students`

- **Description:** Basic CRUD operations for Student data.
- **Curl Examples:**
  ```bash
  # Get All Students
  curl http://localhost:8080/api/v1/students

  # Create a Student (Triggers Validation)
  curl -X POST http://localhost:8080/api/v1/students 
       -H "Content-Type: application/json" 
       -d '{"firstName":"Amit","lastName":"Saha","email":"invalid-email"}'
  ```
- **Learnings:**
    - `@RestController` combines `@Controller` and `@ResponseBody`.
    - `@PathVariable`, `@RequestBody`, and `@Valid` for handling and validating input.

### 2. Configuration Management & Profiles
**Endpoint:** `GET /user` | `GET /rate` | `GET /env`

- **Description:** Demonstrates how to read properties from `application.yml` and environment variables.
- **Profiles:**
    - Run with `dev`: `mvn spring-boot:run -Dspring-boot.run.profiles=dev`
    - Run with `prod`: `mvn spring-boot:run -Dspring-boot.run.profiles=prod`
- **Curl Examples:**
  ```bash
  # Get Profile Data via @ConfigurationProperties
  curl http://localhost:8080/user

  # Get values injected via @Value
  curl http://localhost:8080/rate
  ```
- **Learnings:**
    - **`@Value`**: Quick injection of single properties.
    - **`@ConfigurationProperties`**: Type-safe way to map structured properties to a Java object (`UserProfile.java`).
    - **`Environment` API**: Accessing system environment variables like `JAVA_HOME`.

---

## How to Run
1. Run the application:
   ```bash
   mvn spring-boot:run -pl springBootBasic
   ```
2. Explore APIs via Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Key Learnings
1. **Starters:** How Spring Boot simplifies dependency management.
2. **Layered Architecture:** Using Controllers, DTOs, and Services.
3. **Externalized Config:** Keeping code clean by moving configuration to `.yml` or `.properties` files.
4. **Validation:** Handling `MethodArgumentNotValidException` when `@Valid` fails.
