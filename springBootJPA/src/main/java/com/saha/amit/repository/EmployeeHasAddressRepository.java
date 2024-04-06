package com.saha.amit.repository;

import com.saha.amit.entity.hasRelation.EmployeeHasAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeHasAddressRepository extends JpaRepository<EmployeeHasAddress, Integer> {
}
