package com.studentmanagementsystem.student_management_system.service;

import com.studentmanagementsystem.student_management_system.entity.Course;
import com.studentmanagementsystem.student_management_system.repository.CourseRepository;
import com.studentmanagementsystem.student_management_system.serviceImpl.CourseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService  implements CourseImpl {
    @Autowired
    private CourseRepository repository;

    @Override
    public Course addCourse(Course course) {
        return repository.save(course);
    }

    @Override
    public List<Course> getAllCourse() {
        return repository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(int id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Course> getCourseByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public String deleteById(int id) {
        repository.deleteById(id);
        return "Course Deleted";
    }

    @Override
    public Course updateCourse(Course course) {
        Course existingCourse=repository.findById(course.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
       existingCourse.setCourseName(course.getCourseName());
       existingCourse.setCourseCode(course.getCourseCode());
       existingCourse.setDescription(course.getDescription());
        return existingCourse;
    }
}
