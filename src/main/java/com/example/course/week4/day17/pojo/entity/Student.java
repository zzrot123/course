package com.example.course.week4.day17.pojo.entity;

import lombok.Data;

import java.util.Date;

public class Student {
    private String id;
    private String name;
    private Date lastUpdateTime;
    private boolean isActive;
    private Date registeredTime;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
