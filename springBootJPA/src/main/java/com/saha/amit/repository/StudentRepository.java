package com.saha.amit.repository;

import com.saha.amit.entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    @Query("from Student")
    List<Student> findAllStudents();

    @Query("select st.fName, st.lName from Student st")
    List<Object[]> getAllStudentPartialData();

    @Query("from Student where fName like CONCAT('%',:fName,'%')")
    List<Student> getStudentByName(@Param("fName") String fName);

    @Query("from Student where age>:min and age <:max")
    List<Student> getStudentByAge(@Param("min") int minAge, @Param("max") int maxAge);

    @Query("from Student")
    List<Student> findAllStudentsPage(Pageable pageable);

    @Query(value = "select * from student where age > :min and age < :max  and f_name like CONCAT('%',:fName,'%')", nativeQuery = true)
    List<Student> getStudentNativeQuery(@Param("min") int minAge, @Param("max") int maxAge, @Param("fName") String firstName);
}
