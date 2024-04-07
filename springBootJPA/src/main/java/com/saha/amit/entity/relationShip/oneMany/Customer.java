package com.saha.amit.entity.relationShip.oneMany;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<PhoneNumber> numbers;

    public void addPhone(PhoneNumber phoneNumber){
        if (null != phoneNumber){
            if (null == numbers){
                numbers = new ArrayList<>();
            }
            phoneNumber.setCustomer(this);
            numbers.add(phoneNumber);
        }
    }

}
