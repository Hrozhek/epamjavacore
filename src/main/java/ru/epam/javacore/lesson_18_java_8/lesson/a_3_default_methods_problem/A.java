package ru.epam.javacore.lesson_18_java_8.lesson.a_3_default_methods_problem;

public interface A {
    default void method() {
        System.out.println("This is method A");
    }
}
