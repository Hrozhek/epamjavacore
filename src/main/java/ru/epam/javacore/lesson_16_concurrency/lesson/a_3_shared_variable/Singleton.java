package ru.epam.javacore.lesson_16_concurrency.lesson.a_3_shared_variable;

public class Singleton {

    private static volatile Singleton INSTANCE = null;

/*
    public static Singleton getInstance() {
        if (INSTANCE == null){
            INSTANCE = new Singleton();
        }

        return INSTANCE;
    }

*/
/*
    public static synchronized Singleton getInstance() {
        if (INSTANCE == null){
            INSTANCE = new Singleton();
        }

        return INSTANCE;
    }
    */

    public static Singleton getInstance() {
        if (INSTANCE == null) {

            synchronized (Singleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton();
                }
            }

        }

        return INSTANCE;
    }

    public void action() {
        System.out.println("do action");
    }

}
