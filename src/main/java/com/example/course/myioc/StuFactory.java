package com.example.course.myioc;

import com.example.course.week3.orm.demo4.Student;

public class StuFactory {
    public static StudentService getStudent() {
        return new StudentServiceImpl1();
    }
}
/**
 *   1. requirement
 *      1)
 *      2)
 *      3)
 *   2. interface + common function + abstract class
 *          doGetBean()
 *          doCreateBean()
 *   3. annotations

 */