package com.studentmanagementsystem.student_management_system.controller;

import com.studentmanagementsystem.student_management_system.service.StudentRegiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stud")
public class StudentRegister {
    @Autowired
   private StudentRegiService service;
@PostMapping("/register")
    public com.studentmanagementsystem.student_management_system.entity.StudentRegister register(@RequestBody com.studentmanagementsystem.student_management_system.entity.StudentRegister register)
    {
       return service.registerStudent(register);
    }

}
