package ru.epam.javacore.lesson_15.lesson.b_thread_creation;

public class ThreadUtils {

    public static String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    public static void sleepSec(int sec) throws InterruptedException {
        Thread.sleep(sec * 1000);
    }
}
