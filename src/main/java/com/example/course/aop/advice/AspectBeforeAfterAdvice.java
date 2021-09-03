package com.example.course.aop.advice;

import java.lang.reflect.Method;

public class AspectBeforeAfterAdvice implements BeforeAfterAdvice{

    private Method aspectMethod;
    private Object aspectObj;

    public AspectBeforeAfterAdvice(Method method, Object aspectObj) {
        this.aspectMethod = method;
        this.aspectObj = aspectObj;
    }

    @Override
    public Object execute(Method method, Object[] args, Object target) throws Throwable{
        this.aspectMethod.invoke(aspectObj);

        return null;
    }
}
