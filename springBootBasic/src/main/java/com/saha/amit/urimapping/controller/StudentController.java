package com.saha.amit.urimapping.controller;

import com.saha.amit.urimapping.dto.StudentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@Validated
public class StudentController {

    /**
     * Get a single student by ID
     *
     * @return the student object
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Get Student by ID",
            description = "Fetches a single student using their unique ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Student retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Student not found")
            }
    )
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") int id) {
        StudentDTO studentDTO = new StudentDTO(
                id,
                "Ramesh",
                "J",
                "ramesh@example.com",
                LocalDate.of(1995, 8, 25),
                new BigDecimal("1500.00")
        );
        return ResponseEntity.ok(studentDTO);
    }

    /**
     * Get all students
     *
     * @return list of students
     */
    @GetMapping
    @Operation(
            summary = "Get All Students",
            description = "Retrieves a list of all students",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Students retrieved successfully")
            }
    )
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = new ArrayList<>();
        students.add(new StudentDTO(1, "Ramesh", "J", "ramesh@example.com", LocalDate.of(1995, 8, 25), new BigDecimal("1500.00")));
        students.add(new StudentDTO(2, "Suresh", "Kumar", "suresh@example.com", LocalDate.of(1998, 5, 15), new BigDecimal("1800.00")));
        return ResponseEntity.ok(students);
    }

    /**
     * Create a new student
     *
     * @param studentDTO the student object to be created
     * @return the created student
     */
    @PostMapping
    @Operation(
            summary = "Create Student",
            description = "Creates a new student with the provided details",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Student created successfully"),
                    @ApiResponse(responseCode = "400", description = "Validation error")
            }
    )
    public ResponseEntity<StudentDTO> createStudent(@RequestBody @Valid StudentDTO studentDTO) {
        return ResponseEntity.status(201).body(studentDTO);
    }

    /**
     * Update an existing student
     *
     * @param id      the student ID
     * @param studentDTO the student details to be updated
     * @return the updated student
     */
    @PutMapping("/{id}")
    @Operation(
            summary = "Update Student",
            description = "Updates the details of an existing student",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Student updated successfully"),
                    @ApiResponse(responseCode = "404", description = "Student not found")
            }
    )
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") int id, @RequestBody @Valid StudentDTO studentDTO) {
        studentDTO.setId(id);
        return ResponseEntity.ok(studentDTO);
    }

    /**
     * Delete a student by ID
     *
     * @param id the student ID
     * @return a confirmation message
     */
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete Student",
            description = "Deletes a student by their ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Student deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Student not found")
            }
    )
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int id) {
        return ResponseEntity.ok("Student with ID " + id + " deleted successfully!");
    }
}
