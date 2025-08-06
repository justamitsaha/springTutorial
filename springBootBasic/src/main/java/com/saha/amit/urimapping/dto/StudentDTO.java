package com.saha.amit.urimapping.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Student Data Transfer Object")
public class StudentDTO {

    @NotNull(message = "ID cannot be null")
    @Min(value = 1, message = "ID must be greater than 0")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Unique ID of the student", example = "101", accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    @NotBlank(message = "First name cannot be blank")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Schema(description = "First name of the student", example = "Amit")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Schema(description = "Last name of the student", example = "Saha")
    private String lastName;

    @Email(message = "Email should be valid")
    @Schema(description = "Email address of the student", example = "amit.saha@example.com")
    private String email;

    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Date of birth must be in the past")
    @Schema(description = "Date of birth (in ISO format)", example = "2000-08-15")
    private LocalDate dateOfBirth;

    @NotNull(message = "Amount cannot be null")
    @Min(value = 1, message = "Amount must be greater than 0")
    @Schema(description = "Fee amount", example = "15000.50")
    private BigDecimal fees;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Schema(description = "Password (write-only)", example = "secret123", accessMode = Schema.AccessMode.WRITE_ONLY)
    private String password;

    public StudentDTO(int id, String firstName, String lastName, String email, LocalDate dateOfBirth, BigDecimal fees) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.fees = fees;
    }
}
