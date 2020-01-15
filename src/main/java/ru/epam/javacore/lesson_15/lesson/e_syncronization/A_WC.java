package ru.epam.javacore.lesson_15.lesson.e_syncronization;

import ru.epam.javacore.lesson_15.lesson.b_thread_creation.ThreadUtils;

import java.util.Collections;

public class A_WC {

    private static class Person extends Thread {
        private A_WC wc;
        private String name;
        private int time;
        private Integer monitor;

        public Person(A_WC wc, String name, int time, Integer monitor) {
            this.wc = wc;
            this.name = name;
            this.time = time;
            this.monitor = monitor;
        }

        @Override
        public void run() {
            for (; ; ) {
                //wc.satisfaction(this);
                wc.satisfactionNew(this);
                // wc.satisfaction(this, this.monitor);
                try {
                    ThreadUtils.sleepSec(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*    public synchronized void satisfaction(Person person) {
            System.out.println("---In---");
            try {
                ThreadUtils.sleepSec(person.time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I am " + person.name + " here looooosers!!");
            System.out.println("---Out---\n");
        }
    */

    /*
    public void satisfaction(Person person) {
        synchronized (this) {
            System.out.println("---In---");
            try {
                ThreadUtils.sleepSec(person.time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I am " + person.name + " here looooosers!!");
            System.out.println("---Out---\n");
        }
    }
*/

    public void satisfaction(Person person, Integer i) {

        synchronized (i) {
            System.out.println("---In---");
            System.out.println(ThreadUtils.getCurrentThreadName());
            try {
                ThreadUtils.sleepSec(person.time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I am " + person.name + " here looooosers!!");
            System.out.println("---Out---\n");
        }
    }
/*
    public static synchronized void satisfactionNew(Person person) {
        System.out.println("---In---");
        System.out.println(ThreadUtils.getCurrentThreadName());
        try {
            ThreadUtils.sleepSec(person.time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("I am " + person.name + " here looooosers!!");
        System.out.println("---Out---\n");

    }
    */

    public static void satisfactionNew(Person person) {
        synchronized (A_WC.class) {
            System.out.println("---In---");
            System.out.println(ThreadUtils.getCurrentThreadName());
            try {
                ThreadUtils.sleepSec(person.time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I am " + person.name + " here looooosers!!");
            System.out.println("---Out---\n");
        }
    }

    public static void main(String[] args) {
        A_WC wc = new A_WC();

        final Integer monitor = 5;

        Person person = new Person(wc, "Ivan", 1, monitor);
        person.start();

        Person person2 = new Person(wc, "Petr", 1, monitor);
        person2.start();

    }
}
