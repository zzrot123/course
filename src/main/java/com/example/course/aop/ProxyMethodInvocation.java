package com.example.course.aop;

import com.example.course.aop.advice.interceptor.MethodInterceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProxyMethodInvocation implements MethodInvocation {

    private final Method method;
    private Object[] arguments;
    private final Object target;
    private final List<?> interceptors;

    private Map<Method, Method> matcher = new HashMap<>();

    private int cursor = 0;

    public ProxyMethodInvocation(Method method, Object[] arguments, Object target, List<?> interceptors) {
        this.method = method;
        this.arguments = arguments;
        this.target = target;
        this.interceptors = interceptors;
    }

    @Override
    public Method getMethod() {
        return null;
    }

    @Override
    public Object[] getArguments() {
        return new Object[0];
    }

    @Override
    public Object getTarget() {
        return null;
    }

    @Override
    public Object proceed() throws Throwable{
        if(cursor >= interceptors.size()) {
            return executeMethod();
        }
        MethodInterceptor nextInterceptor = (MethodInterceptor) interceptors.get(cursor);
        cursor++;
        return nextInterceptor.invoke(this);
    }

    private Object executeMethod() throws Throwable{
        return method.invoke(target, arguments);
    }
}
