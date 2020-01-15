package ru.epam.javacore.lesson_15.lesson.b_thread_creation;

public class A_Thread extends Thread {

    @Override
    public void run() {
        for (; ;) {
            System.out.println("I am running '" + Thread.currentThread().getName() + "'");
        }
    }

    public static void main(String[] args) {
        System.out.println("Main '" + Thread.currentThread().getName() + "'");
        A_Thread a = new A_Thread();
        a.start();

        A_Thread b = new A_Thread();
        b.run();
    }
}
