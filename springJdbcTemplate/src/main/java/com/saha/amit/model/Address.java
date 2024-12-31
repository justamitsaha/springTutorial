package com.saha.amit.model;

import jakarta.persistence.Embeddable;
import lombok.*;


@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {
    private String street;
    private String city;
    private String state;
    private String zipCode;
}

