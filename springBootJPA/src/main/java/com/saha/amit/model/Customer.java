package com.saha.amit.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {         //Owning side
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerUuid;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "customerUuid")
    private Profile profile;        //referenced side

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Orders> orders;
}



