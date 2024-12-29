package com.saha.amit.dto;

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
    private String name;
    private String phoneNumber;
    private CustomerDto customerDto;
    private AddressDto addressDto;


    public ProfileDto(Long profileUuid, String email, String name, String phoneNumber) {
        this.profileUuid = profileUuid;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public ProfileDto(Long profileUuid, String email, String name, String phoneNumber, AddressDto addressDto) {
        this.profileUuid = profileUuid;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.addressDto = addressDto;
    }
}