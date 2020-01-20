package ru.epam.javacore.lesson_16_concurrency.a_1_statefull_problem;

public class A_1_Statefull_Quadratic_Eq {

    private float a, b, c;
    private float d;

    public void calculate(float a, float b, float c) {
        d = b * b - 4 * a * c;
    }

    public void getSolutionsAndPrintThem() {
        if (d < 0) {
            System.out.println("No soultions");
        } else if (d == 0) {
            float r = -b / 2 * a;
            System.out.println("Root " + r);
        } else {
            double r1 = (-b + Math.sqrt(d)) / 2 * a;
            double r2 = (-b - Math.sqrt(d)) / 2 * a;
            System.out.println("Root_1 " + r1 + " Root_2 " + r2);
        }
    }
}
