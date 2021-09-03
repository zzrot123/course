package com.example.course.week4.day17.controller;

import com.example.course.week4.day17.pojo.StudentResponse;
import com.example.course.week4.day17.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    private final StudentService ss;

    @Autowired
    public StudentController(@Qualifier("studentServiceImpl1") StudentService ss) {
        this.ss = ss;
    }
    /**
     *  endpoint: /students
     *  http method: get
     *  return pojo
     */
//    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @GetMapping("/students")
    public ResponseEntity<StudentResponse> getAllStudents(@RequestParam(required = false) String age) {
        if(age.length() < 2) {
            throw new IllegalArgumentException("attribute age: " + age + " is not valid");
        }
        return new ResponseEntity<>(ss.retrieveStudents(), HttpStatus.OK);
    }

//    @ExceptionHandler(IllegalArgumentException.class)
//    public String expHandler(IllegalArgumentException ex) {
//        return ex.getMessage();
//    }
}
