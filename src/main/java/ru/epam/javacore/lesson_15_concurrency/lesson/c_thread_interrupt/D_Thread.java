package ru.epam.javacore.lesson_15_concurrency.lesson.c_thread_interrupt;

import ru.epam.javacore.lesson_15_concurrency.lesson.b_thread_creation.ThreadUtils;

import static ru.epam.javacore.lesson_15_concurrency.lesson.b_thread_creation.ThreadUtils.getCurrentThreadName;

public class D_Thread extends Thread {
    private boolean stop = false;

    @Override
    public void run() {
        while (!stop) {
            System.out.println("AAAAA " + getCurrentThreadName());
            try {
                ThreadUtils.sleepSec(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Finish " + getCurrentThreadName());
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public static void main(String[] args) throws InterruptedException {
        D_Thread d_thread = new D_Thread();
        d_thread.start();
        ThreadUtils.sleepSec(1);
        d_thread.setStop(true);

        System.out.println("Main finish");
    }

}
