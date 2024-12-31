package com.saha.amit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long profileUuid;
    private String email;
    private String name;
    private String phoneNumber;
    private String street;
    private String city;
    private String state;
    private String zipCode;

    public ProfileDto(String email, String name, String phoneNumber, String street, String city, String state, String zipCode) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
}