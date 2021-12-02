package com.example.demo.service;

import com.example.demo.model.Course;

import java.util.List;

public interface CourseService {

    List<Course> getCourses();

    void removeCourseById(Long id);
}
