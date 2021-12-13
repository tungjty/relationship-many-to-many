package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_course")
public class Course {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    @SequenceGenerator(
            name =  "course_sequence",
            sequenceName = "course_id_seq",
            allocationSize = 1
    )
    private Long courseId;

    public String courseName;

    // TODO : WE CAN ALSO MAKE MANY-TO-MANY RELATIONSHIP IN STUDENT ENTITY
    @ManyToMany(
            // CascadeType.REMOVE : DELETE COURSE-> DELETE ALL IN MAPPING TABLE & STUDENT
            // IF YOU NEED TO DELETE COURSE ONLY -> REMOVE CascadeType.REMOVE
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY // DEFAULT 'LAZY'
    )
    @JoinTable(
            name = "student_course_mapping", // NAME OF MAPPING TABLE
            joinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseId",
                    foreignKey = @ForeignKey(name = "course_course_id_fk")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "studentId",
                    foreignKey = @ForeignKey(name = "student_student_id_fk")
            )
    )
    private List<Student> students;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public Course(String courseName, List<Student> students) {
        this.courseName = courseName;
        this.students = students;
    }

    //    public void addStudent(Student student) {
//        if(students == null)
//            students = new ArrayList<>();
//        students.add(student);
//    }
}
