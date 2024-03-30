package com.saha.amit.repository;

import com.saha.amit.entity.inheritance.singleTable.Payment;
import org.springframework.data.repository.CrudRepository;

public interface  PaymentRepository extends CrudRepository<Payment, Integer> {
}
