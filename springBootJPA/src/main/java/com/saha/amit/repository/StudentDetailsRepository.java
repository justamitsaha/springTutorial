package com.saha.amit.repository;

import com.saha.amit.entity.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StudentDetailsRepository extends JpaRepository<StudentDetails, Integer>, PagingAndSortingRepository<StudentDetails, Integer> {

    List<StudentDetails> findByfName(String fName);


    List<StudentDetails> findByfNameContains(String fName);

    List<StudentDetails> findByLastNameContaining(String lastName);

    List<StudentDetails> findByRemarksLike(String remarks);

    List<StudentDetails> findBySchoolMarksPercentageBetween(Double marks1, Double marks2);

    List<StudentDetails> findBySchoolMarksPercentageGreaterThan(Double marks);

    List<StudentDetails> findBySpecializationIn(List<String> specialization);

    List<StudentDetails> findByfNameContainingAndLastNameAndRemarksContaining(String fName, String lastName, String remarks);

    List<StudentDetails> findByRemarksContainingAndSpecializationInAndSchoolMarksPercentageBetween( String remarks, List<String> specialization, Double marks1, Double marks2);

}
