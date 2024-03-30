package com.saha.amit.entity.inheritance.tablePerClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "card")
public class CreditCardTablePerClass extends PaymentTablePerClass {

    private String cardNumber;
}
