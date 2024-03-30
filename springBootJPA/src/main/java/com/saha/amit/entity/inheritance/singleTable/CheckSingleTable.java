package com.saha.amit.entity.inheritance.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@DiscriminatorValue("ch")
public class CheckSingleTable extends PaymentSingleTable {

    private  String checkNumber;
}
