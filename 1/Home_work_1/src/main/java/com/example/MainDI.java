package com.example;
//import org.reflections
//import org.reflections.Reflections;:reflections:0.9.12;
//import org.reflections:reflections:0.9.12;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

public class MainDI {
    public static void main(String[] args) {
        new SimpleDIFramework("com.example");
        System.out.println("создали объект SimpleDIFramework");

    }

}
