package com.saha.amit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<OrderDto> orders;

    public CustomerDto(String email, String name, String phoneNumber, String street, String city, String state, String zipCode) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
}



