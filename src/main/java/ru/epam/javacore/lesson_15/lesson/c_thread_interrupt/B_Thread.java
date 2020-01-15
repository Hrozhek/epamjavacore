package ru.epam.javacore.lesson_15.lesson.c_thread_interrupt;

import static ru.epam.javacore.lesson_15.lesson.b_thread_creation.ThreadUtils.getCurrentThreadName;

public class B_Thread extends Thread {

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            System.out.println("AAAAA " + getCurrentThreadName());
        }


        System.out.println("Finish " + getCurrentThreadName());
    }

    public static void main(String[] args) throws InterruptedException {
        B_Thread thread = new B_Thread();
        thread.start();

        Thread.sleep(1000);
        thread.interrupt();
        System.out.println("Finish main " + getCurrentThreadName());
    }
}
