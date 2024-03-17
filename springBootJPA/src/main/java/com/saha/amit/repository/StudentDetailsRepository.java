package com.saha.amit.repository;

import com.saha.amit.entity.Student;
import com.saha.amit.entity.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDetailsRepository extends JpaRepository<StudentDetails,Integer> {
}
