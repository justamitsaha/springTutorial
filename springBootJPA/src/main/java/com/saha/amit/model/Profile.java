package com.saha.amit.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Profile")
public class Profile {      //referenced side
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_uuid")
    private Long profileUuid;

    @Column(name = "email", nullable = false)
    private String email;

    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne(mappedBy = "profile",  fetch = FetchType.LAZY)
    private Customer customer;      //Owning side which has FK

    @Embedded
    private Address address;

}

