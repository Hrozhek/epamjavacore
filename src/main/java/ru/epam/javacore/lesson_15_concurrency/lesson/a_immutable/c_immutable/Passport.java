package ru.epam.javacore.lesson_15_concurrency.lesson.a_immutable.c_immutable;

public class Passport {
    private int seria;
    private int number;

    public Passport(int seria, int number) {
        this.seria = seria;
        this.number = number;
    }

    public int getSeria() {
        return seria;
    }

    public void setSeria(int seria) {
        this.seria = seria;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "seria=" + seria +
                ", number=" + number +
                '}';
    }
}
