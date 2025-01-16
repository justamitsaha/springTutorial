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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class InsertRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method 1: Using update() with direct SQL
    public void insertUserBasic(User user) {
        String sql = "INSERT INTO users (name, email, birth_date) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getBirthDate());
    }

    // Method 2: Using update() with PreparedStatementSetter
    public void insertUserWithPreparedStatement(User user) {
        String sql = "INSERT INTO users (name, email, birth_date) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setDate(3, Date.valueOf(user.getBirthDate()));
            }
        });
    }

    // Method 3: Using SimpleJdbcInsert
    public void insertUserWithSimpleJdbcInsert(User user) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", user.getName());
        parameters.put("email", user.getEmail());
        parameters.put("birth_date", user.getBirthDate());

        Number generatedId = simpleJdbcInsert.executeAndReturnKey(parameters);
        user.setId(generatedId.longValue());
    }

    // Method 4: Batch insert using batchUpdate()
    public void batchInsertUsers(List<User> users) {
        String sql = "INSERT INTO users (name, email, birth_date) VALUES (?, ?, ?)";

        List<Object[]> batchArgs = users.stream()
                .map(user -> new Object[]{
                        user.getName(),
                        user.getEmail(),
                        user.getBirthDate()
                })
                .collect(Collectors.toList());

        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    // Method 5: Using named parameters with NamedParameterJdbcTemplate
    public void insertUserWithNamedParameters(User user) {
        NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

        String sql = "INSERT INTO users (name, email, birth_date) " +
                "VALUES (:name, :email, :birthDate)";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("email", user.getEmail())
                .addValue("birthDate", user.getBirthDate());

        namedTemplate.update(sql, parameters);
    }
}