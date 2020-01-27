package ru.epam.javacore.lesson_18_java_8.lessons.a_1_collection.jdk12;

public interface SuperCollection<T> {
    void add(T t);

    int size();

    default void delete() {
        System.out.println("sdsd");
    }

}
