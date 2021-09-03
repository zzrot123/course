package com.example.course.aop.advice.interceptor;

import com.example.course.aop.MethodInvocation;
import com.example.course.aop.advice.Advice;
import com.example.course.aop.advice.interceptor.MethodInterceptor;

import java.lang.reflect.Method;

public class AroundAdvice implements MethodInterceptor, Advice {
    private Object aspectInstance;
    private Method aspectMethod;

    public AroundAdvice(Method aspectMethod, Object aspectInstance) {
        this.aspectInstance = aspectInstance;
        this.aspectMethod = aspectMethod;
    }


    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        return aspectMethod.invoke(aspectInstance, mi);
    }
}
