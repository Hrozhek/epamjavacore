package ru.epam.javacore.lesson_16_concurrency.a_3_shared_variable;

import java.util.concurrent.atomic.AtomicInteger;

public class A_2_Counter_Solution_2 {

    static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Thread thread_1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                  counter.incrementAndGet();
                }
            }
        });

        Thread thread_2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    counter.incrementAndGet();
                }
            }
        });

        thread_1.start();
        thread_2.start();

        thread_1.join();
        thread_2.join();

        System.out.println(counter);

    }



}
