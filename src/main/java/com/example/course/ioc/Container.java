package com.example.course.ioc;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Dependency injection => IOC (IOC container)
 *  why IOC ?
 * @Component -> mark bean -> container
 * @Autowired -> inject bean
 */
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
                if(a.annotationType() == Component.class) {
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
                    if(a.annotationType() == Autowired.class) {
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


@Component
class StudentRegisterService {
    @Override
    public String toString() {
        return "this is student register service instance : " + super.toString() + "\n";
    }
}

@Component
class StudentApplication {
    @Autowired
    StudentRegisterService studentRegisterService;

    @Override
    public String toString() {
        return "StudentApplication{\n" +
                "studentRegisterService=" + studentRegisterService +
                "}\n";
    }
}

@Component
class Starter {
    @Autowired
    private static StudentApplication studentApplication;
    @Autowired
    private static StudentRegisterService studentRegisterService;

    public static void main(String[] args) throws Exception{
        Container.start();
        System.out.println(studentApplication);
        System.out.println(studentRegisterService);
    }
}
/**
 *  1. add interface
 *  2. all components need to impl interface
 *  3. @Autowired -> inject by type
 *                   if we have multiple implementations of current type => throw exception
 *  4. @Autowired + @Qualifier("name") -> inject by bean name
 *  5. provide constructor injection
 *      @Autowired
 *      public xx(.. ,..) {
 *          .. = ..
 *          .. = ..
 *      }
 *  6. provide setter injection
 *  7. provide different injection scope / bean scope
 *          1. now we only supporting singleton
 *          2. prototype -> @Autowired => you inject a new instance
 */