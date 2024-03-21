package com.saha.amit.entity.oneToOne;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Entity
//@Table(name = "user1")
public class User1 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "user1", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address1 address1;

    private String name;
}
