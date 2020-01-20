package ru.epam.javacore.lesson_16_concurrency.a_1_statefull_problem;

import java.util.ArrayList;
import java.util.List;

public class A_2_Main {

    public static void main(String[] args) {
        List<float[]> inputData = new ArrayList<>();
        inputData.add(new float[]{1, 4, 4});
      //  inputData.add(new float[]{1, -3, 2});
        inputData.add(new float[]{1, -8, 12});
        inputData.add(new float[]{5, 3, 7});
       // inputData.add(new float[]{3, -6, 9});


        List<Thread> threads = new ArrayList<>();
        A_1_Statefull_Quadratic_Eq a_quadratic_eq = new A_1_Statefull_Quadratic_Eq();

        for (float[] in : inputData) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    a_quadratic_eq.calculate(in[0], in[1], in[2]);
                    a_quadratic_eq.getSolutionsAndPrintThem();
                }
            });

            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }
}
