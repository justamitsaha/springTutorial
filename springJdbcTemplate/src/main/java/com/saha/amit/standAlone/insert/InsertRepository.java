package com.saha.amit.standAlone.insert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class InsertRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /* Method 1: Using update() with direct SQL
    Advantages
    Simple and straightforward implementation
    Good for single row insertions
    Easy to understand and maintain

    Disadvantages
    No built-in batch processing capability
    Creates a new PreparedStatement for each execution
    Limited control over PreparedStatement configuration
    No direct support for complex data types without extra handling

    Performance Impact
    Medium memory footprint due to new PreparedStatement creation
    Connection pool usage: One connection per operation
    Performance decreases linearly with number of operations
    Typical throughput: 100-1000 operations/second (varies by setup)
     */
    public void insertUserBasic(User user) {
        String sql = "INSERT INTO users (name, email, birth_date, created_at) VALUES (?, ?, ?, ?)";
        user.setCreatedAt(LocalDateTime.now());  // Set current timestamp
        jdbcTemplate.update(sql,
                user.getName(),
                user.getEmail(),
                user.getBirthDate(),
                user.getCreatedAt()
        );
    }

    /* Method 2: Using update() with PreparedStatementSetter
    Advantages
    Fine-grained control over parameter setting
    Better handling of NULL values and custom types
    Reuses PreparedStatement

    Disadvantages
    More verbose implementation
    Requires more boilerplate code
    Anonymous inner class creation adds complexity
    Manual handling of SQLExceptions required

    Performance Impact
    Better memory usage due to PreparedStatement reuse
    Slightly higher initial overhead due to PreparedStatement setup
    Connection pool usage: One connection per operation, but PreparedStatement is cached
    Typical throughput: 200-1200 operations/second (varies by setup)
     */
    public void insertUserWithPreparedStatement(User user) {
        String sql = "INSERT INTO users (name, email, birth_date, created_at) VALUES (?, ?, ?, ?)";
        user.setCreatedAt(LocalDateTime.now());  // Set current timestamp
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setDate(3, Date.valueOf(user.getBirthDate()));
                ps.setTimestamp(4, Timestamp.valueOf(user.getCreatedAt()));
            }
        });
    }

    /* Method 3: Using SimpleJdbcInsert
    Advantages
    Clean API for insertions
    Automatic handling of generated keys
    Metadata-aware operations

    Disadvantages
    Additional overhead from metadata retrieval
    Limited to insert operations only
    Not suitable for complex SQL statements
    Requires exact column name matching

    Performance Impact
    Higher initial overhead due to metadata retrieval
    Memory overhead from storing table metadata
    Additional database round trip for metadata on first use
    Typical throughput: 150-1000 operations/second (varies by setup)
    Performance benefits visible mainly in long-running applications
     */
    public void insertUserWithSimpleJdbcInsert(User user) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");

        user.setCreatedAt(LocalDateTime.now());  // Set current timestamp
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", user.getName());
        parameters.put("email", user.getEmail());
        parameters.put("birth_date", user.getBirthDate());
        parameters.put("created_at", user.getCreatedAt());

        Number generatedId = simpleJdbcInsert.executeAndReturnKey(parameters);
        user.setId(generatedId.longValue());
    }

    /* Method 4: Batch insert using batchUpdate()
    Advantages
    Highly efficient for multiple records
    Reduced network round trips
    Lower per-record overhead

    Disadvantages
    More complex error handling
    All-or-nothing transaction behavior by default
    Memory consumption increases with batch size
    Need to carefully choose batch size

    Performance Impact
    Highest throughput for multiple records
    Memory usage scales with batch size
    Network efficiency: One round trip per batch instead of per record
    Typical throughput: 1000-10000 operations/second (with optimal batch size)
    Optimal batch size typically between 100-1000 records
     */
    public void batchInsertUsers(List<User> users) {
        String sql = "INSERT INTO users (name, email, birth_date, created_at) VALUES (?, ?, ?, ?)";

        List<Object[]> batchArgs = users.stream()
                .map(user -> {
                    user.setCreatedAt(LocalDateTime.now());  // Set current timestamp
                    return new Object[]{
                            user.getName(),
                            user.getEmail(),
                            user.getBirthDate(),
                            user.getCreatedAt()
                    };
                })
                .collect(Collectors.toList());

        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    /* Method 5: Using named parameters with NamedParameterJdbcTemplate
    Advantages
    More readable and maintainable code
    Reduces parameter ordering errors
    Self-documenting SQL

    Disadvantages
    Slight performance overhead from parameter name resolution
    Additional object creation for parameter maps
    More memory usage due to parameter name storage
    Not supported by all databases

    Performance Impact
    2-5% overhead compared to positional parameters
    Additional memory allocation for parameter names
    Slightly higher CPU usage for parameter resolution
    Typical throughput: 90-950 operations/second (varies by setup)
     */
    public void insertUserWithNamedParameters(User user) {
        NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

        String sql = "INSERT INTO users (name, email, birth_date, created_at) " +
                "VALUES (:name, :email, :birthDate, :createdAt)";

        user.setCreatedAt(LocalDateTime.now());  // Set current timestamp
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("email", user.getEmail())
                .addValue("birthDate", user.getBirthDate())
                .addValue("createdAt", user.getCreatedAt());

        namedTemplate.update(sql, parameters);
    }
}