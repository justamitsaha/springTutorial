package com.saha.amit.entity.hasRelation;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class Address {
    private String streetAddress ;
    private String  city;
    private String  state;
    private String  zipcode;
    private String  country;
}
