package com.saha.amit.entity.inheritance.singleTable;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@DiscriminatorValue("cc")
public class CreditCard extends  Payment{

    private String cardNumber;
}
