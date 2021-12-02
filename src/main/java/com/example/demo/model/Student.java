package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
//@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_student")
public class Student {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @SequenceGenerator(
            name =  "student_sequence",
            sequenceName = "student_id_seq",
            allocationSize = 1
    )
    private Long studentId;

    public String studentName;

    // TODO : WE DEFINE @JoinTable HERE ( BI-DIRECTION RELATIONSHIP )
    @ManyToMany(
            // DONT PUT 'CascadeType.REMOVE' HERE
            cascade = {CascadeType.PERSIST},
            fetch = FetchType.LAZY // DEFAULT 'EAGER'
    )
    @JoinTable(
            name = "student_course_mapping", // NAME OF MAPPING TABLE
            joinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "studentId",
                    foreignKey = @ForeignKey(name = "student_student_id_fk")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseId",
                    foreignKey = @ForeignKey(name = "course_course_id_fk")
            )
    )
    private List<Course> courses;

    public Student(String studentName) {
        this.studentName = studentName;
    }

    public Student(String studentName, List<Course> courses) {
        this.studentName = studentName;
        this.courses = courses;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

//    public List<Course> getCourses() {
//        return courses;
//    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
