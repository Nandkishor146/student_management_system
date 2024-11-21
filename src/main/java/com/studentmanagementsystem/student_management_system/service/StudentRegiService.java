package com.studentmanagementsystem.student_management_system.service;
import com.studentmanagementsystem.student_management_system.entity.StudentRegister;
import com.studentmanagementsystem.student_management_system.repository.StudentRegiRepo;
import com.studentmanagementsystem.student_management_system.serviceImpl.StudentRegiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentRegiService implements StudentRegiImpl {
    @Autowired
    private StudentRegiRepo regiRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(12);


    @Override
    public StudentRegister registerStudent(StudentRegister studentRegister) {
        if (!studentRegister.getRole().equalsIgnoreCase("ADMIN") && !studentRegister.getRole().equalsIgnoreCase("STUDENT")) {
            throw new IllegalArgumentException("Invalid role. Use 'ADMIN' or 'STUDENT'.");
        }
        studentRegister.setPassword(passwordEncoder.encode(studentRegister.getPassword()));
        studentRegister.setRole(studentRegister.getRole().toUpperCase());
        return regiRepo.save(studentRegister);

    }


}











