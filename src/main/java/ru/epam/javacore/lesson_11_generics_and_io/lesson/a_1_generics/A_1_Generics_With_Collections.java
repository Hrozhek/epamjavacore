package ru.epam.javacore.lesson_11_generics_and_io.lesson.a_1_generics;

import java.util.ArrayList;
import java.util.List;

public class A_1_Generics_With_Collections {

    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();
    }

    private static void demoExtends(){
        List<? extends Number> numbers = new ArrayList<Number>();
        List<? extends Number> numbers2 = new ArrayList<Integer>();
        List<? extends Number> numbers3 = new ArrayList<Float>();
    }

    private static void demoSuper(){
        List<? super Integer> ints = new ArrayList<Integer>();
        List<? super Integer> ints2 = new ArrayList<Number>();
        List<? super Integer> ints3 = new ArrayList<Object>();
    }
}
