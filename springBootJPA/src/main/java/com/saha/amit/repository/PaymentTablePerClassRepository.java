package com.saha.amit.repository;

import com.saha.amit.entity.inheritance.tablePerClass.PaymentTablePerClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTablePerClassRepository extends JpaRepository<PaymentTablePerClass,Integer> {
}
