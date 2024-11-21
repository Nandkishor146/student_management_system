package com.studentmanagementsystem.student_management_system.service;

import com.studentmanagementsystem.student_management_system.entity.StudentRegister;
import com.studentmanagementsystem.student_management_system.entity.Users;
import com.studentmanagementsystem.student_management_system.entity.UsersPrincipal;
import com.studentmanagementsystem.student_management_system.repository.StudentRegiRepo;
import com.studentmanagementsystem.student_management_system.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UsersRepository userRepository;

   /* @Autowired
    private StudentRegiRepo regiRepo;*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try to find the user in the `users` table
        Optional<Users> userOptional = Optional.ofNullable(userRepository.findByUsername(username));

        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            return new UsersPrincipal(user.getUsername(), user.getPassword(), user.getRole());
        }

        /*// If not found in `users`, try the `studentRegister` table
        Optional<StudentRegister> studentO = Optional.ofNullable(regiRepo.findByUsername(username));

        if (studentO.isPresent()) {
            StudentRegister student = studentO.get();
            return new UsersPrincipal(student.getUserName(),student.getPassword(), student.getRole());
        }*/

        // Throw exception if user not found in both tables
        throw new UsernameNotFoundException("User not found in any table");
    }

}
