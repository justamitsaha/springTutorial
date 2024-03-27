package com.saha.amit;

import com.github.javafaker.Faker;
import com.saha.amit.entity.Student;
import com.saha.amit.entity.StudentDetails;
import com.saha.amit.entity.enumerated.Semester;
import com.saha.amit.entity.enumerated.Specialization;
import com.saha.amit.entity.enumerated.Status;
import com.saha.amit.repository.StudentDetailsRepository;
import com.saha.amit.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
public class SpringBootJpaApplication implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentDetailsRepository studentDetailsRepository;

    public static void main(String[] args) {
        System.out.println("Swagger URL http://localhost:8080/swagger-ui/index.html#/");
        SpringApplication.run(SpringBootJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("runner");
        //loadData();
    }

    public void loadData() {
        Faker faker = new Faker();
        Random random = new Random();
        for (int i = 0; i < 200; i++) {
            String fName = faker.superhero().name();
            String lName = faker.funnyName().name();

            Student student = Student.builder()
                    .fName(fName)
                    .lName(lName)
                    .status(Status.ACTIVE)
                    .age(random.nextInt(14,36))
                    .build();
            student.setStudentId(student.getFName());

            Date date = faker.date().between(new Date(1985, 12, 12), new Date(1999, 12, 12));
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            StudentDetails studentDetails = StudentDetails.builder()
                    .student(student)
                    .fName(fName)
                    .lastName(lName)
                    .dob(localDate)
                    .semester(Semester.values()[faker.number().numberBetween(1, 9)])
                    .status(Status.ACTIVE)
                    .specialization(Specialization.values()[faker.number().numberBetween(1, 5)])
                    .remarks(faker.howIMetYourMother().catchPhrase())
                    .email(faker.name().firstName() + "@gmail.com")
                    .phoneNumber(faker.phoneNumber().cellPhone())
                    .schoolMarksPercentage(faker.number().numberBetween(60, 101))
                    .cgpa(random.nextFloat(5.0f,10.0f))
                    .build();
            studentRepository.save(student);
            studentDetailsRepository.save(studentDetails);
        }

    }
}