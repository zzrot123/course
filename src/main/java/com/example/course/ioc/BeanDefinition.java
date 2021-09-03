package com.example.course.ioc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *      Map<Class<?>, Object> instanceMap-> for type injection
 *      Map<BeanName String, BeanDefinition> ->
 *      Map<BeanName String, BeanDefinitionHolder> ->



 *      Map<Class<?>, Object> instanceMap-> for type injection
 *      Map<BeanName String, Class<?>> ->
 *
 *
 *      if interface only has one implmentation
 *              instanceMap.put(Implentation.Type, instance);
 *
 *
 *
 * @Autowired
 * StudentService ss; //only 1 impl -> inject impl
 *
 * @Autowired
 * StudentService ss; //two impls -> throw exception
 *
 * @Autowired
 * StudentServiceImpl1 ss; //inject impl1
 *
 * @Autowired
 * StudentService studentServiceImpl1; //by name injection -> inject impl1
 *
 * @Qualifier("studentServiceImpl1")
 * @Autowired
 * StudentService ss; //by name injection -> inject impl1
 *
 *
 *
 */
public class BeanDefinition {
    private String beanName;
    private Class<?> beanClass;
    private String scope;
    //..
}
class BeanDefinitionHolder {
    private BeanDefinition beanDefinition;
    private List<String> aliasNames;
}
