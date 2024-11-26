package com.saha.amit.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profile {      //referenced side
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String phoneNumber;

    @OneToOne(mappedBy = "profile",  fetch = FetchType.EAGER)
    private Customer customer;      //Owning side which has FK


    @Embedded
    private Address address;

}