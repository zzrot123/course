package com.example.course.myioc;

public interface StudentService {
}

class StudentServiceImpl1 implements StudentService{ }
class StudentServiceImpl2 implements StudentService{}


class CalculationService {

    private StudentService ss;

    public CalculationService(StudentService ss) {
        this.ss = ss;
    }

    public int count() {
        //ss..
        return 0;
    }

    public int minue() {
        return 0;
    }

    public static void main(String[] args) {
        CalculationService instance = new CalculationService(StuFactory.getStudent());
        instance.count();
    }
}