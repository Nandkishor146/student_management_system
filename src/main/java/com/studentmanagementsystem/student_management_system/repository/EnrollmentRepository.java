package com.studentmanagementsystem.student_management_system.repository;

import com.studentmanagementsystem.student_management_system.entity.Course;
import com.studentmanagementsystem.student_management_system.entity.Enrollment;
import com.studentmanagementsystem.student_management_system.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Integer> {
    @Query(value = "SELECT * FROM enrollment WHERE student_id = :studentId", nativeQuery = true)
    List<Enrollment> findByStudentId(int studentId);
    @Query(value = "SELECT * FROM enrollment WHERE course_id = :courseId", nativeQuery = true)
    List<Enrollment> findByCourseId(int courseId);

    boolean existsByStudentAndCourse(Student student, Course course);
}
