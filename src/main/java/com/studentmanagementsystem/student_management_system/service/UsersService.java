package com.studentmanagementsystem.student_management_system.service;

import com.studentmanagementsystem.student_management_system.entity.Users;
import com.studentmanagementsystem.student_management_system.repository.UsersRepository;
import com.studentmanagementsystem.student_management_system.serviceImpl.UsersImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService implements UsersImpl {
@Autowired
private UsersRepository usersRepository;
@Autowired
private AuthenticationManager manager;
@Autowired
private JWTService jwtService;


    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
    @Override
    public Users addUser(Users users) {
        if (!users.getRole().equalsIgnoreCase("ADMIN") && !users.getRole().equalsIgnoreCase("STUDENT")) {
            throw new IllegalArgumentException("Invalid role. Use 'ADMIN' or 'STUDENT'.");
        }
        users.setPassword(encoder.encode(users.getPassword()));
        users.setRole(users.getRole().toUpperCase());
        return usersRepository.save(users);
    }

    @Override
    public String deleteUserById(int id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
            return "User deleted successfully.";
        } else {
            return "User not found.";
        }
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }
    public String verify(Users users) {
        try {
            Authentication authentication = manager.authenticate
                    (new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword()));
            if (authentication.isAuthenticated()) {
                // Fetch the user from the database to get the stored role
                Users existingUser = usersRepository.findByUsername(users.getUsername());

                if (existingUser != null) {
                    // Check if the role provided matches the role in the database
                    if (existingUser.getRole().equalsIgnoreCase(users.getRole())) {
                        // If all three match (username, password, and role), generate token
                        return jwtService.generateToken(users.getUsername(), users.getRole());
                    } else {
                        // If the role doesn't match, throw an exception or return a message
                        throw new IllegalArgumentException("Role mismatch. Provided role doesn't match the user role.");
                    }
                } else {
                    throw new RuntimeException("User not found.");
                }
            }
        } catch (Exception e) {
            // Handle authentication failure (incorrect username or password)
            throw new RuntimeException("Invalid username or password", e);
        }

        return "Authentication failed";
    }
}
