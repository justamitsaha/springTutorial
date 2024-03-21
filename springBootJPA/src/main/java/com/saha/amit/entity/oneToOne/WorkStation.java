package com.saha.amit.entity.oneToOne;

import jakarta.persistence.*;
import lombok.*;
//@Entity
//@Table(name = "workstation")
public class WorkStation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "workStation")
    private Employee employee;
    private  String workstationPc;
}
