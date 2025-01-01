package com.saha.amit;


import com.saha.amit.dto.ProfileDto;
import com.saha.amit.repository.CustomerRepositoryJdbc;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.AssertionErrors;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfileRepositoryIntegrationTestStandAlone {

    @Autowired
    private CustomerRepositoryJdbc customerRepositoryJdbc;

    @Test
    public void testFindById() {
        // Arrange
        ProfileDto expectedProfile = new ProfileDto("test@example.com", "Test User", "1234567890",
                "123 Street", "City", "State", "12345");
        expectedProfile.setProfileUuid(11L);

        ProfileDto actualProfile = customerRepositoryJdbc.findById(11L);

        // Assert
//        assertTrue(actualProfile.isPresent());
//        assertEquals("John Doe", result.get().getName());
    }
}
