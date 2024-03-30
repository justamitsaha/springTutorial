package com.saha.amit.entity.inheritance.joined;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "card_joined")
@PrimaryKeyJoinColumn(name = "cardPaymentId")
public class CreditCardJoined extends  PaymentJoined {

    private String cardNumber;
}
