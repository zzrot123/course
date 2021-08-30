package com.example.course.homework;


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
        return Arrays.asList(StudentRegisterServiceImpl1.class,StudentRegisterServiceImpl2.class, StudentApplicationImpl.class, StarterImpl.class);
    }

    private boolean register(List<Class<?>> classes) throws Exception {
        for(Class<?> clazz: classes) {
            Annotation[] annotations = clazz.getAnnotations();
            for(Annotation a: annotations) {
                if(a.annotationType() == Component.class) {
//                    System.out.println(clazz.getSimpleName());
                    objectFactory.put(clazz.getSimpleName(), clazz.getDeclaredConstructor(null).newInstance());
//                    System.out.println(objectFactory.get(clazz.getSimpleName()));
                }
            }
        }
        return true;
    }

    private boolean injectObjects(List<Class<?>> classes) throws Exception{
        for(Class<?> clazz: classes) {
            Field[] fields = clazz.getDeclaredFields();
            //System.out.println(clazz.getSimpleName());
            Object curInstance = objectFactory.get(clazz.getSimpleName());
            for(Field f: fields) {
                Annotation[] annotations = f.getAnnotations();
                System.out.println(annotations.length);
                // check for CustomQualifier
                if(f.isAnnotationPresent(CustomQualifier.class)){
                    Class<?> type = f.getType();
                    Object injectInstance = objectFactory.get(f.getAnnotation(CustomQualifier.class).name());
                    f.setAccessible(true);
                    f.set(curInstance, injectInstance);
                    continue;
                }else if(annotations.length>=2){
                    // multiple implementation of the type, throw exception
                    throw new IllegalArgumentException("multiple implementations of current type is not allowed");
                }
                for(Annotation a: annotations) {
                    if(a.annotationType() == Autowired.class) {
                        // check if it is constructor or setter function or regular assignment
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

//
//@Component
//class StudentRegisterService {
//    @Override
//    public String toString() {
//        return "this is student register service instance : " + super.toString() + "\n";
//    }
//}
//
//@Component
//class StudentApplication {
//    @Autowired
//    StudentRegisterService studentRegisterService;
//
//    @Override
//    public String toString() {
//        return "StudentApplication{\n" +
//                "studentRegisterService=" + studentRegisterService +
//                "}\n";
//    }
//}
//
//@Component
//class Starter {
//    @Autowired
//    private static StudentApplication studentApplication;
//    @Autowired
//    private static StudentRegisterService studentRegisterService;
//
//    public static void main(String[] args) throws Exception{
//        Container.start();
//        System.out.println(studentApplication);
//        System.out.println(studentRegisterService);
//    }
//}

// add interface
interface StudentServices{
}
interface StudentApplications{
}
interface ApplicationRunner{
}

// all components impl interface
@Component
class StudentRegisterServiceImpl1 implements StudentServices {
    @Override
    public String toString() {
        return "this is student register service instance 1 : " + super.toString() + "\n";
    }
}

@Component
class StudentRegisterServiceImpl2 implements StudentServices {
    @Override
    public String toString() {
        return "this is student register service instance 2 : " + super.toString() + "\n";
    }
}

@Component
class StudentApplicationImpl implements StudentApplications {
    // qualifier done
    @CustomQualifier(name = "StudentRegisterServiceImpl1")
    @Autowired
    StudentServices studentRegisterService;

    @Override
    public String toString() {
        return "StudentApplication{\n" +
                "studentRegisterServiceImpl=" + studentRegisterService +
                "}\n";
    }
}

@Component
class StarterImpl implements ApplicationRunner {
    @Autowired
    private static StudentApplicationImpl studentApplicationImpl;
    @Autowired
    private static StudentRegisterServiceImpl1 studentRegisterServiceImpl1;
//    private static StudentApplications studentApplicationImpl;
//    private static StudentServices studentRegisterServiceImpl;

//    @Autowired
//    StarterImpl(StudentApplications studentApplicationImpl, StudentServices studentRegisterServiceImpl){
//        this.studentApplicationImpl = studentApplicationImpl;
//        this.studentRegisterServiceImpl = studentRegisterServiceImpl;
//    }

    public static void main(String[] args) throws Exception{
        Container.start();
        System.out.println(studentApplicationImpl);
        System.out.println(studentRegisterServiceImpl1);
    }
}



/**
 *  1. add interface (done)
 *  2. all components need to impl interface (done)
 *  3. @Autowired -> inject by type
 *                   if we have multiple implementations of current type => throw exception ( ? )
 *  4. @Autowired + @Qualifier("name") -> inject by bean name (done)
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