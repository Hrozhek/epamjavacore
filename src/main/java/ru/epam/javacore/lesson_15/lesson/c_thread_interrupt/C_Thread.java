package ru.epam.javacore.lesson_15.lesson.c_thread_interrupt;

import static ru.epam.javacore.lesson_15.lesson.b_thread_creation.ThreadUtils.getCurrentThreadName;

public class C_Thread extends Thread {

    private boolean stop = false;

    @Override
    public void run() {
        while (!stop) {
            System.out.println("AAAAA " + getCurrentThreadName());
        }

        System.out.println("Finish " + getCurrentThreadName());
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }


}
