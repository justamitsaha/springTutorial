package com.saha.amit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.saha.amit.dto.ProfileDto;
import com.saha.amit.mapper.ProfileRowMapper;
import com.saha.amit.repository.CustomerRepositoryJdbc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

/*
Unit tests are designed to test individual components or methods in isolation.
They verify the behavior of a single unit of code, typically a method or a class, without involving other components or
the application's infrastructure.
Do not load the full application context.
Avoid using Spring's dependency injection framework.
Use mocking frameworks like Mockito to simulate dependencies.
 */
public class ProfileRepositoryUnitTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CustomerRepositoryJdbc customerRepositoryJdbc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        ProfileDto expectedProfile = new ProfileDto("test@example.com", "Test User", "1234567890", "123 Street", "City", "State", "12345");
        expectedProfile.setProfileUuid(1L);

        when(jdbcTemplate.queryForObject(any(String.class), any(Object[].class), any(ProfileRowMapper.class)))
                .thenReturn(expectedProfile);

        ProfileDto actualProfile = customerRepositoryJdbc.findById(1L);

        assertEquals(expectedProfile, actualProfile);
    }
}

