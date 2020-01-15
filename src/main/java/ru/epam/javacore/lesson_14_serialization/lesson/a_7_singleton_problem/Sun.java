package ru.epam.javacore.lesson_14_serialization.lesson.a_7_singleton_problem;

import java.io.Serializable;

public final class Sun implements Serializable{
    public static final Sun INSTANCE = new Sun("Sun star");

    private String name;

    private Sun(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

//    @Override
//    public String toString() {
//        return "Sun{" +
//                "name='" + name + '\'' +
//                '}';
//    }
}
