package com.example.course.ioc;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Container {
    private final Map<String, Object> objectFactory = new HashMap<>();

    public static void start() throws Exception{
        Container c = new Container();
        List<Class<?>> classes = c.scan();
        c.register(classes);
        c.injectObjects(classes);
    }

    private List<Class<?>> scan() {
        return Arrays.asList(StudentRegisterService.class, StudentApplication.class, Starter.class);
    }

    private boolean register(List<Class<?>> classes) throws Exception {
        for(Class<?> clazz: classes) {
            Annotation[] annotations = clazz.getAnnotations();
            for(Annotation a: annotations) {
                if(a.annotationType() == MyService.class) {
                    objectFactory.put(clazz.getSimpleName(), clazz.getDeclaredConstructor(null).newInstance());
                }
            }
        }
        return true;
    }

    private boolean injectObjects(List<Class<?>> classes) throws Exception{
        for(Class<?> clazz: classes) {
            Field[] fields = clazz.getDeclaredFields();
            Object curInstance = objectFactory.get(clazz.getSimpleName());
            for(Field f: fields) {
                Annotation[] annotations = f.getAnnotations();
                for(Annotation a: annotations) {
                    if(a.annotationType() == Inject.class) {
                        Class<?> type = f.getType();
                        Object injectInstance = objectFactory.get(type.getSimpleName());
                        f.setAccessible(true);
                        f.set(curInstance, injectInstance);
                    }
                }
            }
        }
        return true;
    }
}


@MyService
class StudentRegisterService {
    @Override
    public String toString() {
        return "this is student register service instance : " + super.toString() + "\n";
    }
}

@MyService
class StudentApplication {
    @Inject
    StudentRegisterService studentRegisterService;

    @Override
    public String toString() {
        return "StudentApplication{\n" +
                "studentRegisterService=" + studentRegisterService +
                "}\n";
    }
}

@MyService
class Starter {
    @Inject
    private static StudentApplication studentApplication;

    public static void main(String[] args) throws Exception{
        Container.start();
        System.out.println(studentApplication);
    }
}