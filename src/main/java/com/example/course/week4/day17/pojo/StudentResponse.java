package com.example.course.week4.day17.pojo;

import com.example.course.week4.day17.pojo.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentResponse {
    private List<StudentDTO> students = new ArrayList<>();
    private int totalCount = 0;

    public StudentResponse(List<StudentDTO> students, int totalCount) {
        this.students = students;
        this.totalCount = totalCount;
    }

    public List<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
