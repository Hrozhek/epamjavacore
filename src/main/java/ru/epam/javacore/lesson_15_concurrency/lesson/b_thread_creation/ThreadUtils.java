package ru.epam.javacore.lesson_15_concurrency.lesson.b_thread_creation;

public class ThreadUtils {

    public static String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    public static void sleepSec(int sec) throws InterruptedException {
        Thread.sleep(sec * 1000);
    }
}
