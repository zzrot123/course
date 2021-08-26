package com.example.course.week3.orm.demo4;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teacher")
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "stu")
    private List<Teacher_Student> teacher_students = new ArrayList<>();

    public List<Teacher_Student> getTeacher_students() {
        return teacher_students;
    }

    public void setTeacher_students(Teacher_Student teacher_students) {
        this.teacher_students.add(teacher_students);
    }
}
