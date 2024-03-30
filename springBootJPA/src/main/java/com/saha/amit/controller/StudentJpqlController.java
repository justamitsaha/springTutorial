package com.saha.amit.controller;

import com.saha.amit.entity.Student;
import com.saha.amit.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentJpqlController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/getAllStudent")
    public ResponseEntity<List<Student>> getAllStudent() {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentRepository.findAllStudents());
    }

    @GetMapping("/getAllStudentPartialData")
    public ResponseEntity<List<Student>> getAllStudentPartialData() {
        List<Student> st = new ArrayList<>();
        studentRepository.getAllStudentPartialData().forEach(s -> {
            Student student = new Student();
            student.setFName((String) s[0]);
            student.setLName((String) s[1]);
            st.add(student);
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(st);
    }

    @GetMapping("/getStudentByName")
    public ResponseEntity<List<Student>> getStudentByName(@RequestParam String firstName) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentRepository.getStudentByName(firstName));
    }


    @GetMapping("/getStudentByAge")
    public ResponseEntity<List<Student>> getStudentByAge(@RequestParam int minAge, @RequestParam int maxAge) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentRepository.getStudentByAge(minAge, maxAge));
    }

    @GetMapping("/findAllStudentsPage")
    public ResponseEntity<List<Student>> findAllStudentsPage(@RequestParam int pageNumber, @RequestParam int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentRepository.findAllStudentsPage(pageable));
    }

    @GetMapping("/findAllStudentsPageSort")
    public ResponseEntity<List<Student>> findAllStudentsPageSort(@RequestParam int pageNumber, @RequestParam int pageSize) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "age"));
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentRepository.findAllStudentsPage(pageable));
    }

    @GetMapping("/getStudentNativeQuery")
    public ResponseEntity<List<Student>> getStudentNativeQuery(@RequestParam int minAge, @RequestParam int maxAge, @RequestParam String firstName) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentRepository.getStudentNativeQuery(minAge, maxAge, firstName));
    }

}
