package ru.epam.javacore.lesson_16_concurrency.lesson.a_3_shared_variable;

public class A_1_Counter_Solution {

    static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread_1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                   // counter++;
                    inc();
                }
            }
        });

        Thread thread_2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    //counter++;
                    inc();
                }
            }
        });

        thread_1.start();
        thread_2.start();

        thread_1.join();
        thread_2.join();

       // System.out.println(counter);
        System.out.println(getCounter());

    }

    private static synchronized void inc(){
        counter++;
    }

    private static synchronized int getCounter(){
        return counter;
    }

}
