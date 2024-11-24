package com.saha.amit.repository;

import com.saha.amit.model.Customer;
import com.saha.amit.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByProfileIn(List<Profile> profiles);

}

