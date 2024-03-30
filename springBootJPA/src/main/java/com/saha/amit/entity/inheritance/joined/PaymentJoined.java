package com.saha.amit.entity.inheritance.joined;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "payment_joined")
public class PaymentJoined {
    @Id
    private int paymentId;
    private int amount;
}
