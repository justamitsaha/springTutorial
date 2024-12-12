package com.saha.amit.repository;

import com.saha.amit.dto.CustomerDto;
import com.saha.amit.model.Customer;
import com.saha.amit.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //List<Customer> findByProfileIn(List<Profile> profiles);

    @Query("SELECT c FROM Customer c " +
            "JOIN FETCH c.profile p " +
            "WHERE c.customerUuid = :id")
    Customer findCustomersById(@Param("id") Long id);

    @Query("SELECT c FROM Customer c " +
            "JOIN FETCH c.profile p " +
            "LEFT JOIN FETCH c.orders " +
            "WHERE c.customerUuid = :id")
    Customer getCustomerProfileOrder(@Param("id") Long id);

//    @Query("SELECT new com.example.CustomerDto(c.customerUuid, c.name, p.email, p.phoneNumber, a.street, a.city, a.state, a.zipCode) " +
//            "FROM Customer c " +
//            "JOIN c.profile p " +
//            "JOIN p.address a " +
//            "WHERE c.customerUuid = :id")
//    CustomerDto findCustomerProfileById(@Param("id") Long id);

    @Query("SELECT c FROM Customer c JOIN FETCH c.orders WHERE c.profile IN :profiles")
    List<Customer> findByProfileIn(@Param("profiles") List<Profile> profiles);


    @Query("SELECT c FROM Customer c " +
            "JOIN FETCH c.orders " +
            "JOIN FETCH c.profile p " +
            "WHERE p.email LIKE %:email%")
    List<Customer> findByEmailContainingWithOrders(@Param("email") String email);

}

