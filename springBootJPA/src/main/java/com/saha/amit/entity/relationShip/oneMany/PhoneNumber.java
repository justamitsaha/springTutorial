package com.saha.amit.entity.relationShip.oneMany;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String number;
    private String type;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
