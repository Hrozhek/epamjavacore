package ru.epam.javacore.lesson_11_generics_and_io.lesson.a_3_generics_list;

import java.util.ArrayList;
import java.util.List;

public class A_3_Generics_With_Collections {
    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();
    }

    private static void demoExtends(){
        List<? extends Number> numbers = new ArrayList<Number>();
        List<? extends Number> numbers2 = new ArrayList<Integer>();
        List<? extends Number> numbers3 = new ArrayList<Float>();

        mutate1(numbers2);
    }
    //Producer EXTENDS - read
    private static void mutate1(List<? extends Number> numbers){
        for (Number number : numbers) {
        }
    }

    private static void demoSuper(){
        List<? super Integer> ints = new ArrayList<Integer>();
        List<? super Integer> ints2 = new ArrayList<Number>();
        List<? super Integer> ints3 = new ArrayList<Object>();
    }

    //Consumer S
    //PECS
    private static void mutate2(List<? super Integer> list){
        list.add(88);
        for (Object number:list){

        }
    }
}
