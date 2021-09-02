package com.example.course.week4.day17.service;

import com.example.course.week4.day17.pojo.StudentResponse;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    StudentResponse retrieveStudents();
}
