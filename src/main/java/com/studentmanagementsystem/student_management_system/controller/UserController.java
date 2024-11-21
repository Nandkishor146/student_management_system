package com.studentmanagementsystem.student_management_system.controller;
import com.studentmanagementsystem.student_management_system.entity.Users;
import com.studentmanagementsystem.student_management_system.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UsersService usersService;
     @PostMapping("/addUser")
    public Users saveUser(@RequestBody Users users)
    {
        return usersService.addUser(users);
    }
    @DeleteMapping("/deleteById/{id}")
    public String deleteById(@PathVariable int id)
    {
        return usersService.deleteUserById(id);
    }
    @GetMapping("/getAllUsers")
    public List<Users> findAll()
    {
        return usersService.getAllUsers();
    }
@PostMapping("/login")
    public String login(@RequestBody Users users)
    {
return usersService.verify(users);
    }

}
