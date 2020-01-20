package ru.epam.javacore.lesson_15_concurrency.lesson.b_thread_creation;

public class B_Thread_Creation_With_runnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Hello world " + ThreadUtils.getCurrentThreadName());
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new B_Thread_Creation_With_runnable());
        thread.start();
        System.out.println("Main finish");
    }
}
