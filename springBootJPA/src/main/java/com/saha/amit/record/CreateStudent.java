package com.saha.amit.record;

import com.saha.amit.entity.enumerated.Semester;
import com.saha.amit.entity.enumerated.Specialization;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudent {
    @NotEmpty(message = "Please provide First name")
    private String fName;
    @NotEmpty(message = "Please provide Last name")
    private String lName;
    @Past(message = "Please provide valid date of birth")
    private LocalDate dob;
    private Semester semester;
    @NotNull(message = "Please provide proper stream of student")
    private Specialization specialization;
    private String remarks;
    @Email(message = "Email should be valid")
    private String email;
    @NotEmpty
    @Size(min = 10, max = 10 , message = "Phone number should be only 10 digit")
    @Pattern(regexp = "[0-9]+", message = "Phone number should only have numeric digits")
    private String phoneNumber;
    @NotNull(message = "Please provide 12th marks between 50-100")
    @Max(value = 100, message = "Marks % can't be greater than 100 ")
    @Min(value = 50, message = "Score below 50% not allowed")
    private float schoolMarksPercentage;
}
