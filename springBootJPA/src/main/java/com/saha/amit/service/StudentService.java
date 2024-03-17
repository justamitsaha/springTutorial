package com.saha.amit.service;

import com.saha.amit.entity.Student;
import com.saha.amit.entity.StudentDetails;
import com.saha.amit.entity.enumerated.Semester;
import com.saha.amit.entity.enumerated.Status;
import com.saha.amit.record.CreateStudent;
import com.saha.amit.repository.StudentDetailsRepository;
import com.saha.amit.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .lName(createUser.getLName())
                .dob(createUser.getDob())
                .semester((null != createUser.getSemester()) ? createUser.getSemester(): Semester.ONE)
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

}
