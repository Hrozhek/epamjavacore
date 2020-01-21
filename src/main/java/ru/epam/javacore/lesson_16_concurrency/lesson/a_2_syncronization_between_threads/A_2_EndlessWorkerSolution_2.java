package ru.epam.javacore.lesson_16_concurrency.lesson.a_2_syncronization_between_threads;

import ru.epam.javacore.lesson_16_concurrency.lesson.common.ThreadUtils;

public class A_2_EndlessWorkerSolution_2 {

    private static volatile boolean needWork = true;

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (needWork) {
                    // System.out.println("I am working");
                }

                System.out.println("I am worker, i have finished work");
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        ThreadUtils.sleepSec(2);
        needWork = false;
        System.out.println(needWork);
        System.out.println("Work is finbished");
    }



}
