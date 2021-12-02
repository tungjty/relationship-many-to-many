package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.service.CourseService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/api")
public class HomeController {

    private final CourseService courseService;
    private final StudentService studentService;

    @Autowired
    public HomeController(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @GetMapping(path = "course/all")
    public List<Course> getCourses() {
        return courseService.getCourses();
    }

    @GetMapping(path = "student/all")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    // DELETE COURSE -> DELETE STUDENT
    @DeleteMapping(path = "course/delete/{id}")
    public String deleteCourseById(@PathVariable("id") Long id) {
        courseService.removeCourseById(id);
        return "COURSE WITH STUDENT(S) WAS REMOVED SUCCESSFULLY";
    }

    // DELETE STUDENT -> DO NOT DELETE COURSE
    @DeleteMapping(path = "student/delete/{id}")
    public String deleteStudentById(@PathVariable("id") Long id) {
        studentService.removeStudentById(id);
        return "STUDENT WAS REMOVED ONLY";
    }
}
