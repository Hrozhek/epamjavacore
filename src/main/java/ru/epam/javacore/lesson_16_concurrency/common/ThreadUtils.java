package ru.epam.javacore.lesson_16_concurrency.common;

public class ThreadUtils {

    private ThreadUtils() {


    }

    public static void sleepSec(int sec) {
        try {
            Thread.sleep(1000 * sec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
