package ru.epam.javacore.lesson_15.lesson.c_thread_interrupt;

import ru.epam.javacore.lesson_15.lesson.b_thread_creation.ThreadUtils;

import static ru.epam.javacore.lesson_15.lesson.b_thread_creation.ThreadUtils.getCurrentThreadName;

public class E_Thread extends Thread {
    private boolean stop = false;

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            System.out.println("AAAAA " + getCurrentThreadName());
            try {
                ThreadUtils.sleepSec(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
                this.interrupt();
            }
        }

        System.out.println("Finish " + getCurrentThreadName());
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public static void main(String[] args) throws InterruptedException {
        E_Thread d_thread = new E_Thread();
        d_thread.start();

        ThreadUtils.sleepSec(1);
        d_thread.interrupt();

        System.out.println("Main finish");
    }

}
