package com.studentmanagementsystem.student_management_system.service;

import com.studentmanagementsystem.student_management_system.entity.Student;
import com.studentmanagementsystem.student_management_system.repository.StudentRepository;
import com.studentmanagementsystem.student_management_system.serviceImpl.StudentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements StudentImpl {
    @Autowired
    private StudentRepository repository;
    @Override
    public Student addStudent(Student student) {
        return repository.save(student);
    }

    @Override
    public List<Student> getAllStudent() {
        return repository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(int id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Student> getStudentByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public String deleteStudentById(int id) {
        repository.deleteById(id);
        return "Student Deleted";
    }

    @Override
    public Student updateStudent(Student student) {
        Student existingStudent = repository.findById(student.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        existingStudent.setStudentName(student.getStudentName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setDob(student.getDob());
        existingStudent.setGrade(student.getGrade());
        return repository.save(existingStudent);
    }
}
