package com.example.course.test;

import com.example.course.aop.MethodInvocation;
import com.example.course.aop.annotation.After;
import com.example.course.aop.annotation.Around;
import com.example.course.aop.annotation.Before;

public class TeacherAspect {
    @Before
    @After
    public void m1() throws Throwable {
        System.out.println("before + after");
    }

    @Around
    public void m2(MethodInvocation mi) throws Throwable {
        System.out.println("1");
        mi.proceed();
        System.out.println("2");
    }
}



