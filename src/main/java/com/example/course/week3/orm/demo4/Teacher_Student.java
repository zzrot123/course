package com.example.course.week3.orm.demo4;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "teacher_student")
@Data
public class Teacher_Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "s_id")
    private Student stu;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "t_id")
    private Teacher teacher;
}
