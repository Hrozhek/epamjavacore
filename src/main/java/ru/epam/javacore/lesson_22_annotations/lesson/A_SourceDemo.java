package ru.epam.javacore.lesson_22_annotations.lesson;

public class A_SourceDemo {
    private final String hdd;
    private final int ram;


    @java.beans.ConstructorProperties({"hdd", "ram"})
    public A_SourceDemo(String hdd, int ram) {
        this.hdd = hdd;
        this.ram = ram;
    }
}
