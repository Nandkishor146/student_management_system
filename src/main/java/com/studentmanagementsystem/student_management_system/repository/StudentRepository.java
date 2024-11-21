package com.studentmanagementsystem.student_management_system.repository;

import com.studentmanagementsystem.student_management_system.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query(value = "SELECT * FROM student WHERE student_name = :studentName", nativeQuery = true)
    Optional<Student> findByName(@Param("studentName")String name);
}
