package com.saha.amit.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressDto {
    private String street;
    private String city;
    private String state;
    private String zipCode;
}

