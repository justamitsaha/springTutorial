package com.saha.amit.repository;

import com.saha.amit.entity.inheritance.singleTable.PaymentSingleTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentSingleTableRepository extends CrudRepository<PaymentSingleTable, Integer> {
}
