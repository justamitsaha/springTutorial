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
@Table(name = "bankcheck")
public class CheckTablePerClass extends PaymentTablePerClass {

    private  String checkNumber;
}
