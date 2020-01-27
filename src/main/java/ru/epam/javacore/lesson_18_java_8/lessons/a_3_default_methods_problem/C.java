package ru.epam.javacore.lesson_18_java_8.lessons.a_3_default_methods_problem;

public class C implements A, B {
    @Override
    public void method() {
        A.super.method();
        B.super.method();
    }
}
