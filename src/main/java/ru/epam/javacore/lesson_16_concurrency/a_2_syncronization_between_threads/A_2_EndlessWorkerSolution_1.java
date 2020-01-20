package ru.epam.javacore.lesson_16_concurrency.a_2_syncronization_between_threads;

import ru.epam.javacore.lesson_16_concurrency.common.ThreadUtils;

public class A_2_EndlessWorkerSolution_1 {

    private static boolean needWork = true;

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (isNeedWork()) {
                    // System.out.println("I am working");
                }

                System.out.println("I am worker, i have finished work");
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        ThreadUtils.sleepSec(2);
        setNeedWork(false);
        System.out.println(needWork);
        System.out.println("Work is finbished");
    }

    private static synchronized void setNeedWork(boolean needWork) {
        A_2_EndlessWorkerSolution_1.needWork = needWork;
    }

    private static synchronized boolean isNeedWork() {
        return A_2_EndlessWorkerSolution_1.needWork;
    }
}
