package com.saha.amit.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class Customer {         //Owning side
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerUuid;

    private String customerName;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "customerUuid")
    private Profile profile;        //referenced side

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Orders> orders;
}



