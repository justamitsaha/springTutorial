package com.saha.amit.configManagement.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "user")
public class UserProfile {

    private String names;
    private String ssn;
    private int age;
    private List<String> address;

}
