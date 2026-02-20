# Spring JdbcTemplate: Direct SQL Interaction

This module explores using **Spring's `JdbcTemplate`** for low-level database interaction without the overhead of a full ORM like JPA. It focuses on writing manual SQL and mapping results to Java objects.

## Key Features
- **Raw SQL Control:** Full power of SQL including CTEs and complex joins.
- **Row Mapping:** Manual mapping using `RowMapper` and `ResultSetExtractor`.
- **Database Schema Management:** Automatic initialization via `schema.sql` and `data.sql`.
- **Transaction Management:** Handled by Spring's `@Repository`.

---

## Repository Deep Dive & Learnings

### 1. Simple CRUD & Schema
- **File:** `SimpleQueryRepository.java`
- **Learning:** How to use `jdbcTemplate.update()` for INSERT/UPDATE/DELETE and `jdbcTemplate.queryForObject()` for single row retrieval.

### 2. Complex Joins & Result Mapping
**Endpoint (Internal):** `findAllCustomersWithProfilesAndOrders`

- **The Challenge:** Mapping a "One-to-Many" relationship (Customer to Orders) using flat SQL results.
- **The Solution:** **`ResultSetExtractor`**.
- **Learnings:** 
    - A `RowMapper` is used when 1 row in SQL = 1 Java object.
    - A `ResultSetExtractor` is used when you need to "collapse" multiple SQL rows into a single nested Java object (e.g., 1 Customer with a `List<Order>`).

### 3. Advanced SQL: CTEs (Common Table Expressions)
**Method:** `findCustomersWithProfilesAndOrdersNameAndCount`

- **The Query:** Uses a `WITH RankedOrders AS (...)` clause.
- **Learnings:** Demonstrates that `JdbcTemplate` can handle any complex SQL that the underlying database (H2/MySQL) supports, which is often difficult in JPA.

---

## How to Run
1. Run the module:
   ```bash
   mvn spring-boot:run -pl springJdbcTemplate
   ```
2. The application uses `schema.sql` and `data.sql` in `src/main/resources` to pre-populate the H2 database on every start.

## Curl Examples (if Controller is enabled)
*Note: Ensure `CustomerControllerJdbc` or similar is active.*
```bash
# Example search
curl "http://localhost:8080/api/jdbc/customers?email=gmail"
```

---

## JPA vs. JdbcTemplate: When to use what?

| Feature | JPA (Hibernate) | JdbcTemplate |
| :--- | :--- | :--- |
| **Effort** | Low (Auto-generates SQL) | High (Manual SQL) |
| **Control** | Medium (JPQL/Criteria) | High (Full SQL) |
| **Performance** | Good (with tuning) | Excellent (No overhead) |
| **Best for** | Standard CRUD, complex domains | Reporting, bulk updates, complex SQL |
