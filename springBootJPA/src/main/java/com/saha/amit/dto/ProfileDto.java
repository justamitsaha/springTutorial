package com.saha.amit.dto;

import com.saha.amit.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private Long profileUuid;
    private String email;
    private String phoneNumber;
    private CustomerDto customerDto;
    private AddressDto addressDto;
}