package com.studentmanagementsystem.student_management_system.serviceImpl;

import com.studentmanagementsystem.student_management_system.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseImpl {

    Course addCourse(Course course);
    List<Course> getAllCourse();
    Optional<Course> getCourseById(int id);
    Optional<Course> getCourseByName(String name);
    String deleteById(int id);
    Course updateCourse(Course course);
}
