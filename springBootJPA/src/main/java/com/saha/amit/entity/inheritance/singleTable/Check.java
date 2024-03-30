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
@DiscriminatorValue("ch")
public class Check extends  Payment{

    private  String checkNumber;
}
