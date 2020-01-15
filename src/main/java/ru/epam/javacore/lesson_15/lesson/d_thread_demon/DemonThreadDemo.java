package ru.epam.javacore.lesson_15.lesson.d_thread_demon;

import ru.epam.javacore.lesson_15.lesson.b_thread_creation.ThreadUtils;

public class DemonThreadDemo extends Thread {
    public DemonThreadDemo() {
        setDaemon(true);
    }

    @Override
    public void run() {
        for (; ; ) {
            System.out.println("Im working");
            try {
                ThreadUtils.sleepSec(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new DemonThreadDemo().start();
        System.out.println("Main finish");
    }
}
