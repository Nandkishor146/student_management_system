package com.studentmanagementsystem.student_management_system.serviceImpl;

import com.studentmanagementsystem.student_management_system.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentImpl {

    Student addStudent(Student student);
    List<Student> getAllStudent();
    Optional<Student> getStudentById(int id);
    Optional<Student> getStudentByName(String name);
    String deleteStudentById(int id);
    Student updateStudent(Student student);

}
