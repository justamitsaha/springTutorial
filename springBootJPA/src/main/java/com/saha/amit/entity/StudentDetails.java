package com.saha.amit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saha.amit.entity.enumerated.Semester;
import com.saha.amit.entity.enumerated.Specialization;
import com.saha.amit.entity.enumerated.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int studentDetailsId;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "unique_student_id", referencedColumnName = "student_Id")
    private Student student;

    private String fName;
    @Column(nullable = false)
    private String lastName;
    @CreationTimestamp
    private String createdDate;
    @UpdateTimestamp
    private String updateDate;
    private LocalDate dob;
    @Enumerated(EnumType.ORDINAL)
    private Semester semester;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    private String remarks;
    private float cgpa;
    private String email;
    private String phoneNumber;
    private float schoolMarksPercentage;

}
