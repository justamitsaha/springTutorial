package com.saha.amit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.Objects;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long customerUuid;
    @Schema(description = "Email  of user", example = "helloDev@mailinator.com")
    private String email;
    @Schema(description = "Name of customer", example = "Laura")
    private String name;
    @Schema(description = "User Phone no", example = "9999999911")
    private String phoneNumber;
    @Schema(description = "Street", example = "Spencer street")
    private String street;
    @Schema(description = "City", example = "Torrance")
    private String city;
    @Schema(description = "CA", example = "California")
    private String state;
    @Schema(description = "Zip code", example = "90503")
    private String zipCode;

    public CustomerDto(String email, String name, String phoneNumber, String street, String city, String state, String zipCode) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto that = (CustomerDto) o;
        return Objects.equals(customerUuid, that.customerUuid) && Objects.equals(email, that.email) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerUuid, email, phoneNumber);
    }
}



