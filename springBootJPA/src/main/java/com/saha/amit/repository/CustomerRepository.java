package com.saha.amit.repository;

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

    @Query("SELECT c FROM Customer c JOIN FETCH c.orders WHERE c.profile IN :profiles")
    List<Customer> findByProfileIn(@Param("profiles") List<Profile> profiles);

    @Query("SELECT c FROM Customer c " +
            "JOIN FETCH c.orders " +
            "JOIN c.profile p " +
            "WHERE p.email LIKE %:email%")
    List<Customer> findByEmailContainingWithOrders(@Param("email") String email);

}

