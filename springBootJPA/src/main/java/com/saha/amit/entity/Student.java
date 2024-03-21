package com.saha.amit.entity;

import com.saha.amit.entity.enumerated.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @Column(name = "student_id")
    private String studentId;

    //@NotNull Not just for JPA entities but for any bean validation
    @Column(nullable = false)  //specific to JPA
    private String fName;
    @Column(nullable = false)
    private String lName;
    @CreationTimestamp
    private String createdDate;
    @Enumerated(EnumType.STRING)
    private Status status;

    public void setStudentId(String fName) {
        LocalDateTime currentTime = LocalDateTime.now();
        int currentYear = currentTime.getYear();
        this.studentId = fName + "_" + System.currentTimeMillis() + "_" + currentYear;
    }

}
