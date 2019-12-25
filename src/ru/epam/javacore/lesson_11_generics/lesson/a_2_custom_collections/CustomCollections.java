package ru.epam.javacore.lesson_11_generics.lesson.a_2_custom_collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomCollections {
    private static class Animal{}
    private static class Pet extends Animal{}
    private static class Cat extends Pet{}
    private static class Dog extends Pet{}


    private static void demo_3(){
        List<Cat> dest = new ArrayList<>();
        List<Cat> src = new ArrayList<>();
        dest.addAll(src);

        List<Dog> dest2 = new ArrayList<>();
        List<Dog> src2 = new ArrayList<>();
        dest2.addAll(src2);

        CustomCollections.<Cat>copy2(dest, src);

        List<Animal> animalsDest = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();
        animalsDest.addAll(cats);
        CustomCollections.<Animal>copy2(animalsDest, cats);


        List<Animal> aNewDest = new ArrayList<>();
        List<Dog> srcDog = new ArrayList<>();
        CustomCollections.<Pet>copy3(aNewDest, srcDog);

        //Collections.copy();
     }

    private static<T> void copy3(List<? super T> dest, List<? extends T> src){
        for (T t : src) {
            dest.add(t);
        }
    }
    private static void demo_2(){
        List<Cat> dest = new ArrayList<>();
        List<Cat> src = new ArrayList<>();
        dest.addAll(src);

        List<Dog> dest2 = new ArrayList<>();
        List<Dog> src2 = new ArrayList<>();
        dest2.addAll(src2);

        CustomCollections.<Cat>copy2(dest, src);

        List<Animal> animalsDest = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();
        animalsDest.addAll(cats);
        CustomCollections.<Animal>copy2(animalsDest, cats);


        List<Animal> aNewDest = new ArrayList<>();
        List<Dog> srcDog = new ArrayList<>();
        //CustomCollections.<Pet>copy2(aNewDest, srcDog);
     }

    private static<T> void copy2(List<T> dest, List<? extends T> src){
        for (T t : src) {
            dest.add(t);
        }
    }
       private static void demo_1(){
        List<Cat> dest = new ArrayList<>();
        List<Cat> src = new ArrayList<>();
        dest.addAll(src);

        List<Dog> dest2 = new ArrayList<>();
        List<Dog> src2 = new ArrayList<>();
        dest2.addAll(src2);

        CustomCollections.<Cat>copy1(dest, src);

        List<Animal> animalsDest = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();
        animalsDest.addAll(cats);
        //copy1(animalsDest, cats);
    }

    private static<T> void copy1(List<T> dest, List<T> src){
        for (T t : src) {
            dest.add(t);
        }
    }


}
