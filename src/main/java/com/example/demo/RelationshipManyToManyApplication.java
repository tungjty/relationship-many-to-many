package com.example.demo;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class RelationshipManyToManyApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelationshipManyToManyApplication.class, args);
	}

	@Bean
	CommandLineRunner run(CourseRepository courseRepository,
						  StudentRepository studentRepository) {
		return args -> {

			// SAVE COURSE -> SAVE STUDENT
			Student maria = new Student("1- Maria Carey");
			Student tommy = new Student("2- Tommy Le");
			List<Student> students_us = List.of(maria, tommy);

			Course javaCourse = new Course("01. Java For Dev", students_us);

			courseRepository.save(javaCourse);

			// SAVE STUDENT -> SAVE COURSE
			Course swiftCourse = new Course("02. Swift for Beginner");
			Course pythonCourse = new Course("03. Jump To Python");
			List<Course> listCourse = List.of(swiftCourse, pythonCourse);
			Student tunghoang = new Student("3- Tung Hoang", listCourse);

			studentRepository.save(tunghoang);
		};
	}
}
