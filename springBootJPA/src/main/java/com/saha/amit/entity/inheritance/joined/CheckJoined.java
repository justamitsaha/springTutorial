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
@Table(name = "check_joined")
@PrimaryKeyJoinColumn(name = "checkPaymentId")
public class CheckJoined extends  PaymentJoined{

    private  String checkNumber;
}
