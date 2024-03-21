package com.saha.amit.entity.oneToOne;

import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Entity
//@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "emp_workstation",
            joinColumns =
                    { @JoinColumn(name = "employee_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "workstation_id", referencedColumnName = "id") })
    private WorkStation workStation;

    private String employeeName;
}
