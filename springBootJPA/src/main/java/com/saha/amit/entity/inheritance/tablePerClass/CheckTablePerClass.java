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
@Table(name = "bank_check")
public class CheckTablePerClass extends PaymentTablePerClass {

    private  String checkNumber;
}
