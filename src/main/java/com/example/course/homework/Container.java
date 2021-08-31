package com.example.course.homework;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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

    // should i do constructor injection inside register or in injectObjects?
    private boolean register(List<Class<?>> classes) throws Exception {
        for(Class<?> clazz: classes) {
            Annotation[] annotations = clazz.getAnnotations();
            for(Annotation a: annotations) {
                if(a.annotationType() == Component.class) {
                    //System.out.println(clazz.getSimpleName());
                    if(clazz.getSimpleName().equals("StarterImpl")){
                        // init starterimpl with constructor
//                        System.out.println(clazz.getDeclaredConstructors()[0].getParameters()[0].getName());
//                        System.out.println(clazz.getDeclaredConstructors()[0].getParameters()[1].getName());
                        objectFactory.put(clazz.getSimpleName(),clazz.getDeclaredConstructors()[0].newInstance(
                                objectFactory.get(clazz.getDeclaredConstructors()[0].getParameters()[0].getName()),
                                objectFactory.get(clazz.getDeclaredConstructors()[0].getParameters()[1].getName())
                        ));
//                        System.out.println(objectFactory.get(clazz.getDeclaredConstructors()[0].getParameters()[0].getName()));
//                        System.out.println(objectFactory.get(clazz.getDeclaredConstructors()[0].getParameters()[1].getName()));
//
//                        System.out.println(objectFactory);
                    }else {
                        objectFactory.put(clazz.getSimpleName(), clazz.getDeclaredConstructor(null).newInstance());
                    }
                    //System.out.println(objectFactory.get(clazz.getSimpleName()));
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
                //System.out.println("the length of annotations is: "+annotations.length+"\n");
                // check for CustomQualifier

                for(Annotation a: annotations) {
                    //System.out.println(a.getClass().getSimpleName());
                    if(a.annotationType() == Autowired.class) {
                        if(f.isAnnotationPresent(CustomQualifier.class)){
                            Class<?> type = f.getType();
                            Object injectInstance = objectFactory.get(f.getAnnotation(CustomQualifier.class).name());
                            f.setAccessible(true);
                            f.set(curInstance, injectInstance);
                            continue;
                        }else if(annotations.length>=2){
                            // multiple implementation of the type, throw exception
                            throw new IllegalArgumentException("multiple implementations of current type is not allowed");
                        }else{
                            Class<?> type = f.getType();
                            Object injectInstance = objectFactory.get(type.getSimpleName());
                            f.setAccessible(true);
                            f.set(curInstance, injectInstance);
                        }

                    }
                }
            }
            // setter injection
            Method[] methods = clazz.getDeclaredMethods();
            for(Method m : methods){
                Annotation[] annotations = m.getAnnotations();
                for(Annotation a: annotations) {
                    m.setAccessible(true);
                    m.invoke(curInstance,5);
                }
            }

            //System.out.println(objectFactory.get(clazz.getSimpleName()));
        }
        return true;
    }
}

// 1. add interface
interface StudentServices{
    int getX();
}
interface StudentApplications{
}
interface ApplicationRunner{
}

// 2. all components impl interface
@Component
class StudentRegisterServiceImpl1 implements StudentServices {

    // 6. setter injection
    static int x = 3;

    @Autowired
    void SetX(int x){
        this.x = x;
    }

    @Override
    public int getX(){
        return x;
    }


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

    @Override
    public int getX(){
        return -1;
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
//    @Autowired
//    private static StudentApplicationImpl studentApplicationImpl;
//    @Autowired
//    private static StudentRegisterServiceImpl1 studentRegisterServiceImpl1;
    private static StudentApplications studentApplicationImpl;
    private static StudentServices studentRegisterServiceImpl1;

    @Autowired
    StarterImpl(StudentApplications StudentApplicationImpl, StudentServices StudentRegisterServiceImpl1){
        // hardcode name for
        StarterImpl.studentApplicationImpl = StudentApplicationImpl;
        StarterImpl.studentRegisterServiceImpl1 = StudentRegisterServiceImpl1;
    }

    @Override
    public String toString() {
        return "this is StarterImpl instance : " + super.toString() + "\n";
    }

    public static void main(String[] args) throws Exception{
        Container.start();
        //System.out.println(StarterImpl);
        System.out.println(StarterImpl.studentApplicationImpl);
        System.out.println(StarterImpl.studentRegisterServiceImpl1);
        // check if setter injection works
        System.out.println(studentRegisterServiceImpl1.getX());
    }
}



/**
 *  1. add interface (done)
 *  2. all components need to impl interface (done)
 *  3. @Autowired -> inject by type
 *                   if we have multiple implementations of current type => throw exception ( ? )
 *  4. @Autowired + @Qualifier("name") -> inject by bean name (done)
 *  5. provide constructor injection (done)
 *      @Autowired
 *      public xx(.. ,..) {
 *          .. = ..
 *          .. = ..
 *      }
 *  6. provide setter injection (done)
 *  7. provide different injection scope / bean scope
 *          1. now we only supporting singleton
 *          2. prototype -> @Autowired => you inject a new instance
 */