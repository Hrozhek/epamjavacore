package ru.epam.javacore.lesson_15.lesson.c_thread_interrupt;

import static ru.epam.javacore.lesson_15.lesson.b_thread_creation.ThreadUtils.getCurrentThreadName;

public class C_ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        C_Thread thread = new C_Thread();
        thread.start();

        Thread.sleep(1000);
        thread.setStop(true);
        System.out.println("Finish main " + getCurrentThreadName());
    }
}
