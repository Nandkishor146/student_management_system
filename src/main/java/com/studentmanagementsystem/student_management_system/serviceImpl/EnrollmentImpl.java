package com.studentmanagementsystem.student_management_system.serviceImpl;

import com.studentmanagementsystem.student_management_system.entity.Enrollment;

import java.util.List;

public interface EnrollmentImpl {
    Enrollment enrollStudent(int studentId, int courseId);
    List<Enrollment> getAllEnrollments();
    List<Enrollment> getEnrollmentsByStudentId(int studentId);
    List<Enrollment> getEnrollmentsByCourseId(int courseId);
     String deleteEnrollment(int id);
}
