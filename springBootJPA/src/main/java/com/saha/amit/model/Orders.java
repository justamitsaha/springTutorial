package com.saha.amit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;


@Entity
@Getter
@Setter
//@AllArgsConstructor       //This was causing payments to get called with unwanted queries
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "order_uuid", updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    private String orderUuid;
    // private UUID orderUuid;      // has issue with mySql works with H2

    private String orderNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;

    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_uuid"),
            inverseJoinColumns = @JoinColumn(name = "product_uuid")
    )
    private List<Product> products;
}

