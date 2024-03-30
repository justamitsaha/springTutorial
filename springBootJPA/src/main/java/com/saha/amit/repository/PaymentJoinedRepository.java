package com.saha.amit.repository;

import com.saha.amit.entity.inheritance.joined.PaymentJoined;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJoinedRepository  extends JpaRepository<PaymentJoined, Integer> {
}
