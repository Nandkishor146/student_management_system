package com.studentmanagementsystem.student_management_system.repository;

import com.studentmanagementsystem.student_management_system.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
   /* @Query("select u from users where u.username= username")
    Users findByUsername(@Param("username") String username);*/

    @Query(value = "SELECT * FROM users WHERE role = :role", nativeQuery = true)
    Users findByRole(@Param("role") String role);
}
