package com.example.course.test;

import com.example.course.aop.MethodInvocation;
import com.example.course.aop.annotation.After;
import com.example.course.aop.annotation.Around;
import com.example.course.aop.annotation.Before;

public class TeacherAspect {
    @Before
    public void m1() throws Throwable {
        System.out.println("before");
    }

    @After
    public void m2() throws Throwable {
        System.out.println("after");
    }

    @Around
    public void m3(MethodInvocation mi) throws Throwable {
        System.out.println("around before logic");
        mi.proceed();
        System.out.println("around after logic");
    }
}



