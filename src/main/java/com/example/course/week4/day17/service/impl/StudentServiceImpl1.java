package com.example.course.week4.day17.service.impl;

import com.example.course.week4.day17.pojo.StudentDTO;
import com.example.course.week4.day17.pojo.StudentResponse;
import com.example.course.week4.day17.pojo.entity.Student;
import com.example.course.week4.day17.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class StudentServiceImpl1 implements StudentService {

    @Override
    public StudentResponse retrieveStudents() {
        Student s1 = new Student("1", "Tom");
        Student s2 = new Student("2", "Jerry");
        StudentDTO st1 = new StudentDTO(s1.getId(), s1.getName());
        StudentDTO st2 = new StudentDTO(s2.getId(), s2.getName());
        List<StudentDTO> students = Arrays.asList(st1, st2);
        return new StudentResponse(students, students.size());
    }
}
