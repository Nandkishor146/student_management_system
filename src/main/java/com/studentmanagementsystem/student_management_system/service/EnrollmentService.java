package com.studentmanagementsystem.student_management_system.service;

import com.studentmanagementsystem.student_management_system.entity.Course;
import com.studentmanagementsystem.student_management_system.entity.Enrollment;
import com.studentmanagementsystem.student_management_system.entity.Student;
import com.studentmanagementsystem.student_management_system.repository.CourseRepository;
import com.studentmanagementsystem.student_management_system.repository.EnrollmentRepository;
import com.studentmanagementsystem.student_management_system.repository.StudentRepository;
import com.studentmanagementsystem.student_management_system.serviceImpl.EnrollmentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnrollmentService implements EnrollmentImpl {
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public Enrollment enrollStudent(int studentId, int courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (enrollmentRepository.existsByStudentAndCourse(student, course)) {
            throw new RuntimeException("Student already enrolled in this course");
        }
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDate.now());

        return enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudentId(int studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourseId(int courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    @Override
    public String deleteEnrollment(int id) {
        enrollmentRepository.deleteById(id);
        return "Deleted Successfully";
    }
}
