package com.saha.amit.controller;

import com.saha.amit.entity.Student;
import com.saha.amit.entity.StudentDetails;
import com.saha.amit.record.CreateStudent;
import com.saha.amit.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentDetails")
public class StudentJPAController {

    @Autowired
    StudentService studentService;

    @PostMapping("/createStudent")
    public ResponseEntity<StudentDetails> save(@RequestBody @Valid CreateStudent createUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createUser(createUser));
    }

    @GetMapping("/getAllStudentDetails")
    public ResponseEntity<List<StudentDetails>> getAllStudentDetails(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.status(HttpStatus.FOUND).body(studentService.getAllStudentDetails(pageNumber, pageSize));
    }

    @GetMapping("/findById")
    public StudentDetails findById(Integer studentDetailsId) {
        // return ResponseEntity.status(HttpStatus.FOUND).body(studentService.findById(studentDetailsId));
        if (null == studentDetailsId) {
            System.out.println("empty");
            throw new RuntimeException();
        }
        return studentService.findById(studentDetailsId);
    }

    @GetMapping("/getAllStudentDetailsSorted")
    public ResponseEntity<List<StudentDetails>> getAllStudentDetailsSorted(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.status(HttpStatus.FOUND).body(studentService.getAllStudentDetailsSorted(pageNumber, pageSize));
    }

    @GetMapping("/getAllStudent")
    public ResponseEntity<List<Student>> getAllStudent() {
        return ResponseEntity.status(HttpStatus.FOUND).body(studentService.getAllStudent());
    }

    @GetMapping("/findByfName")
    public ResponseEntity<List<StudentDetails>> findByfName(@RequestParam String fName) {
        return ResponseEntity.status(HttpStatus.FOUND).body(studentService.findByfName(fName));
    }

    @GetMapping("/findByfNameContains")
    public ResponseEntity<List<StudentDetails>> findByfNameContains(@RequestParam String fName) {
        return ResponseEntity.status(HttpStatus.FOUND).body(studentService.findByfNameContains(fName));
    }

    @GetMapping("/findByLastNameContaining")
    public ResponseEntity<List<StudentDetails>> findByLastNameContaining(@RequestParam String lastName) {
        return ResponseEntity.status(HttpStatus.FOUND).body(studentService.findByLastNameContaining(lastName));
    }

    @GetMapping("/findByRemarksLike")
    public ResponseEntity<List<StudentDetails>> findByRemarksLike(@RequestParam String remarks) {
        return ResponseEntity.status(HttpStatus.FOUND).body(studentService.findByRemarksLike(remarks));
    }

    @GetMapping("/findBySchoolMarksPercentageBetween")
    public ResponseEntity<List<StudentDetails>> findBySchoolMarksPercentageBetween(@RequestParam Double marks1, @RequestParam Double marks2) {
        return ResponseEntity.status(HttpStatus.FOUND).body(studentService.findBySchoolMarksPercentageBetween(marks1, marks2));
    }

    @GetMapping("/findBySchoolMarksGreaterThan")
    public ResponseEntity<List<StudentDetails>> findBySchoolMarksPercentageGreaterThan(@RequestParam Double marks) {
        return ResponseEntity.status(HttpStatus.FOUND).body(studentService.findBySchoolMarksPercentageGreaterThan(marks));
    }

    @GetMapping("/findBySpecializationIn")
    public ResponseEntity<List<StudentDetails>> findBySpecializationIn(@RequestParam List<String> specialization) {
        return ResponseEntity.status(HttpStatus.FOUND).body(studentService.findBySpecializationIn(specialization));
    }

    @GetMapping("/findByfNameAndLastNameAndRemarksContaining")
    public ResponseEntity<List<StudentDetails>> findByfNameAndLastNameAndRemarksContaining(@RequestParam String fName, @RequestParam String lastName, @RequestParam String remarks) {
        return ResponseEntity.status(HttpStatus.FOUND).body(studentService.findByfNameAndLastNameAndRemarksContaining(fName, lastName, remarks));
    }


    @GetMapping("/findByRemarksContainingAndSpecializationInAndSchoolMarksPercentageBetween")
    public ResponseEntity<List<StudentDetails>> findByRemarksContainingAndSpecializationInAndSchoolMarksPercentageBetween(@RequestParam String remarks, @RequestParam List<String> specialization, @RequestParam Double marks1, @RequestParam Double marks2) {
        return ResponseEntity.status(HttpStatus.FOUND).body(studentService.findByRemarksContainingAndSpecializationInAndSchoolMarksPercentageBetween(remarks, specialization, marks1, marks2));
    }

}
