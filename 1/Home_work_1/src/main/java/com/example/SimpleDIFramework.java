package com.example;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SimpleDIFramework {
    public String basePackagesToScan;

    public SimpleDIFramework(String basePackagesToScan) {
        this.basePackagesToScan = basePackagesToScan;
        Reflections reflections = new Reflections(basePackagesToScan, new TypeAnnotationsScanner(),
                new FieldAnnotationsScanner(), new MethodAnnotationsScanner(), new SubTypesScanner());
        Set<Class<?>> annotatedSimpleComponent = reflections.getTypesAnnotatedWith(SimpleComponent.class);
        List<Object> objects = new ArrayList<>();
        annotatedSimpleComponent.forEach(c -> {
            try {
                objects.add(c.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        for(Object o:objects){
            for (Field field:o.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(AutowireSimpleComponent.class)) {
                    for (Object ob : objects) {
                        if (field.getType().getSimpleName().equals(ob.getClass().getSimpleName())) {
                            try {
                                field.setAccessible(true);
                                field.set(o, ob);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }
        }
        for(Object o:objects){
            for (Method method :  o.getClass().getDeclaredMethods()){
                if(method.isAnnotationPresent(AfterDependenciected.class)){
                    try {
                        method.setAccessible(true);
                        method.invoke(o,null);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }

            }

        }


    }




}
