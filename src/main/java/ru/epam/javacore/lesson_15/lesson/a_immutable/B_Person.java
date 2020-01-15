package ru.epam.javacore.lesson_15.lesson.a_immutable;

public class B_Person {
    private final String name;
    private final int age;

    public B_Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
