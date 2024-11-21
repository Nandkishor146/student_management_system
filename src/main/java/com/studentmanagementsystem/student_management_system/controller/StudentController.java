package com.studentmanagementsystem.student_management_system.controller;

import com.studentmanagementsystem.student_management_system.entity.Student;
import com.studentmanagementsystem.student_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
@PostMapping("/addStudent")
@PreAuthorize("hasRole('ADMIN')")
    public Student saveStudent(@RequestBody Student student)
    {
        return studentService.addStudent(student);
    }
    @GetMapping("/getAllStudents")
    @PreAuthorize("hasRole('ADMIN')")
     public List<Student> findAllStudent()
    {
        return studentService.getAllStudent();
    }

    @GetMapping("/getById/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<Student> findStudentById(@PathVariable int id)
    {
        return studentService.getStudentById(id);
    }
    @GetMapping("/getByName/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<Student> findStudentByName(@PathVariable String name)
    {
        return studentService.getStudentByName(name);
    }
    @DeleteMapping("deleteById/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteStudentById(@PathVariable int id)
    {
       studentService.deleteStudentById(id);
        return "Student Deleted";
    }
    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public Student updateStudent(@RequestBody Student student)
    {
        return studentService.updateStudent(student);
    }

}
