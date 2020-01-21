package ru.epam.javacore.lesson_15_concurrency.lesson.a_immutable;

public final class A_Point {
    public static void main(String[] args) {
        A_Point point = new A_Point(10, 22);
    }

    private final int x;
    private final int y;

    public A_Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
