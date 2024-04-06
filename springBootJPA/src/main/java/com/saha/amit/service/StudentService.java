package com.saha.amit.service;

import com.saha.amit.entity.Student;
import com.saha.amit.entity.StudentDetails;
import com.saha.amit.entity.enumerated.Semester;
import com.saha.amit.entity.enumerated.Status;
import com.saha.amit.model.CreateStudent;
import com.saha.amit.repository.StudentDetailsRepository;
import com.saha.amit.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentDetailsRepository studentDetailsRepository;

    public StudentDetails createUser(CreateStudent createUser) {
        Student student = Student.builder()
                .fName(createUser.getFName())
                .lName(createUser.getLName())
                .status(Status.ACTIVE)
                .build();
        student.setStudentId(student.getFName());

        StudentDetails studentDetails = StudentDetails.builder()
                .student(student)
                .fName(createUser.getFName())
                .lastName(createUser.getLName())
                .dob(createUser.getDob())
                .semester((null != createUser.getSemester()) ? createUser.getSemester() : Semester.ONE)
                .status(Status.ACTIVE)
                .specialization(createUser.getSpecialization())
                .remarks(createUser.getRemarks())
                .email(createUser.getEmail())
                .phoneNumber(createUser.getPhoneNumber())
                .schoolMarksPercentage(createUser.getSchoolMarksPercentage())
                .build();
        studentRepository.save(student);
        return studentDetailsRepository.save(studentDetails);
    }

    public List<StudentDetails> getAllStudentDetails(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<StudentDetails> page = studentDetailsRepository.findAll(pageable);
        List<StudentDetails> lst = new ArrayList<>();
        page.forEach(studentDetails -> lst.add(studentDetails));
        return lst;
    }

    public StudentDetails findById(int studentDetailsId) {
        return studentDetailsRepository.findById(studentDetailsId).get();
    }



    public List<StudentDetails> getAllStudentDetailsSorted(int pageNumber, int pageSize) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "specialization"), new Sort.Order(Sort.Direction.DESC, "schoolMarksPercentage"));
        //Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "schoolMarksPercentage"));
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<StudentDetails> page = studentDetailsRepository.findAll(pageable);
        List<StudentDetails> lst = new ArrayList<>();
        page.forEach(studentDetails -> lst.add(studentDetails));
        return lst;
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public List<StudentDetails> findByfName(String fName) {
        return studentDetailsRepository.findByfName(fName);
    }

    public List<StudentDetails> findByfNameContains(String fName) {
        return studentDetailsRepository.findByfNameContains(fName);
    }

    public List<StudentDetails> findByLastNameContaining(String lastName) {
        return studentDetailsRepository.findByLastNameContaining(lastName);
    }

    public List<StudentDetails> findByRemarksLike(String remarks) {
        return studentDetailsRepository.findByRemarksLike(remarks);
    }

    public List<StudentDetails> findBySchoolMarksPercentageBetween(Double marks1, Double marks2) {
        return studentDetailsRepository.findBySchoolMarksPercentageBetween(marks1, marks2);
    }

    public List<StudentDetails> findBySchoolMarksPercentageGreaterThan(Double marks) {
        return studentDetailsRepository.findBySchoolMarksPercentageGreaterThan(marks);
    }

    public List<StudentDetails> findBySpecializationIn(List<String> specialization) {
        return studentDetailsRepository.findBySpecializationIn(specialization);
    }

    public List<StudentDetails> findByfNameAndLastNameAndRemarksContaining(String fName, String lastName, String remarks) {
        return studentDetailsRepository.findByfNameContainingAndLastNameAndRemarksContaining(fName, lastName, remarks);
    }

    public List<StudentDetails> findByRemarksContainingAndSpecializationInAndSchoolMarksPercentageBetween(String remarks, List<String> specialization, Double marks1, Double marks2) {
        return studentDetailsRepository.findByRemarksContainingAndSpecializationInAndSchoolMarksPercentageBetween(remarks, specialization, marks1, marks2);
    }


}
