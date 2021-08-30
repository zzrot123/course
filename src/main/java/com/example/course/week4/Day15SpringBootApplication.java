package com.example.course.week4;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *  mark beans
 *      class level: @Component, @Service,  @Repository
 *      method level: @Bean
 *      scope : singleton / prototype / request / session / global session
 *  inject beans
 *      @Autowired
 *      ByType / ByName
 *      field injection
 *      constructor injection
 *          A {autowire b}
 *          B {autowire a}
 *      setter injection
 *
 *  spring boot vs (spring war file)
 *      1. auto configuration
 *      2. starter main method
 *      3. embedded tomcat
 *      4. package spring boot -> jar
 */
@SpringBootApplication
public class Day15SpringBootApplication {

    private static StudentService ss1;
    private static StudentService ss2;
    @Autowired
    public Day15SpringBootApplication(
            @Qualifier("stuServiceImpl1") StudentService ss1,
            @Qualifier("stuServiceImpl1") StudentService ss2
    ) {
        Day15SpringBootApplication.ss1 = ss1;
        Day15SpringBootApplication.ss2 = ss2;
    }

    public static void main(String[] args) {
        SpringApplication.run(Day15SpringBootApplication.class, args);
        /*
            1. this is impl1
            2. this is impl2
            3. multiple implementation exception
            4. null pointer exception
         */
        //ss1.print();

        /*
              print impl1
         */
//        ss1.print();
        System.out.println(ss1);
        ss1.print();
    }
}

@Component
interface StudentService {
    void print();
}
@Component
@Scope("prototype")
//stuServiceImpl1
class StuServiceImpl1 implements StudentService{
    @Override
    public void print() {
        System.out.println("this is impl1 ");
    }
}
@Component
//stuServiceImpl2
class StuServiceImpl2 implements StudentService{
    @Override
    public void print() {
        System.out.println("this is impl2 ");
    }
}

@Aspect
@Component
class StuServiceAspect {
    @Around(value = "myPointCutting()")
    public void logging(ProceedingJoinPoint mi) throws Throwable {
        System.out.println("this is pre logging from aspect");
        mi.proceed();
        System.out.println("this is after logging from aspect");
    }

    @Pointcut(value = "within(com.example.course.week4..*)")
    public void myPointCutting(){

    }
}
