package com.studentmanagementsystem.student_management_system.serviceImpl;

import com.studentmanagementsystem.student_management_system.entity.Users;

import java.util.List;


public interface UsersImpl {
    Users addUser(Users users);
    String deleteUserById(int id);
    List<Users> getAllUsers();
   String verify(Users users);
}
