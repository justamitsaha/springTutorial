package com.saha.amit.entity.inheritance.singleTable;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "p_mode", discriminatorType = DiscriminatorType.STRING)
public abstract class Payment {
    @Id
    private int id;
    private int amount;

}
