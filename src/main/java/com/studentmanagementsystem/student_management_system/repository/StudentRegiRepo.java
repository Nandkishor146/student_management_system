package com.studentmanagementsystem.student_management_system.repository;


import com.studentmanagementsystem.student_management_system.entity.StudentRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface StudentRegiRepo extends JpaRepository<StudentRegister,Integer> {

   /* StudentRegister findByUsername(String username);


    @Query(value = "SELECT * FROM student_register WHERE role = :role", nativeQuery = true)
    StudentRegister findByRole(@Param("role") String role);
*/
}
