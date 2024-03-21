package com.saha.amit.entity.oneToOne;

import com.saha.amit.entity.oneToOne.User1;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Entity
//@Table(name = "address1")
public class Address1 {

    @Id
    @Column(name = "user_id")
    private Long id;;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User1 user1;

    private String address;
}
