package com.saha.amit.repository;

import com.saha.amit.entity.relationShip.oneMany.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer,Long> {

    public List<Customer> findByNameContaining(String name);
}
