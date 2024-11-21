package com.studentmanagementsystem.student_management_system.controller;

import com.studentmanagementsystem.student_management_system.entity.Course;
import com.studentmanagementsystem.student_management_system.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
//http://localhost:9292/course/deledById
@RestController
@RequestMapping("/course")
@PreAuthorize("hasRole('ADMIN')")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @PostMapping("/addCourse")
    @PreAuthorize("hasRole('ADMIN')")
    public Course addCourse(@RequestBody Course course)
    {
        return courseService.addCourse(course);
    }
    @GetMapping("/getAllCourse")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Course> findAllCourse() {
        return  courseService.getAllCourse();
    }
    @GetMapping("/getById/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<Course> findCourseById(@PathVariable int id) {
        return courseService.getCourseById(id);
    }
@GetMapping("/getByName/{name}")
@PreAuthorize("hasRole('ADMIN')")
    public Optional<Course> findCourseByName(@PathVariable String name) {
        return courseService.getCourseByName(name);
    }
    @DeleteMapping("/deledById/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteById(@PathVariable int id) {
       courseService.deleteById(id);
        return "Course Deleted";
    }
    @PutMapping("/updateCourse")
    @PreAuthorize("hasRole('ADMIN')")
    public Course updateCourse(@RequestBody Course course)
    {
        return courseService.updateCourse(course);
    }
}
