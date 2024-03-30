package com.saha.amit.entity.inheritance.singleTable;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "paymentSingleTable")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "p_mode", discriminatorType = DiscriminatorType.STRING)
public abstract class PaymentSingleTable {
    @Id
    private int id;
    private int amount;

}
