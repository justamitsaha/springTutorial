package com.saha.amit.entity.hasRelation;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employee")
public class EmployeeHasAddress {
    @Id
    private String id;
    private String name;
    @Embedded
    private Address address;
}
