package com.studentmanagementsystem.student_management_system.repository;

import com.studentmanagementsystem.student_management_system.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
    @Query(value = "SELECT * FROM course WHERE course_name = :name", nativeQuery = true)
    Optional<Course> findByName(String name);
}
