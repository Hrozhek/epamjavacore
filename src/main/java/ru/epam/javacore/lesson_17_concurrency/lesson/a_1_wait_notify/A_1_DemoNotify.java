package ru.epam.javacore.lesson_17_concurrency.lesson.a_1_wait_notify;

import java.util.ArrayList;
import java.util.List;

public class A_1_DemoNotify {
    private static volatile boolean awake = false;

    private static class Producer extends Thread {
        private List<String> data;
        private final Object monitor;

        public Producer(List<String> data, Object monitor) {
            this.data = data;
            this.monitor = monitor;
        }

        @Override
        public void run() {
            synchronized (monitor) {
                for (int i = 0; i < 10; i++) {
                    sout("Get data!");
                    data.add("Data_" + i);
                }

                sout("Data ready! Now i will call notify!");
                awake = true;
                monitor.notify();
                sout("Monitor released");
            }
        }
    }

    private static class Consumer extends Thread {
        private List<String> dataToWrite;
        private final Object monitor;
        private volatile boolean started = false;

        public Consumer(List<String> dataToWrite, Object monitor) {
            this.dataToWrite = dataToWrite;
            this.monitor = monitor;
        }

        public boolean isStarted() {
            return started;
        }

        @Override
        public void run() {
            started = true;
            synchronized (monitor) {
                try {
                    prepareHardDrive();
                    sout("Waiting for data ");

                    while (!awake) {
                        monitor.wait();
                    }
                    sout("Got notify signal");
                    if (dataToWrite != null) {
                        for (String s : dataToWrite) {
                            sout("Write data to hard drive '" + s + "'");
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


        }

        private void prepareHardDrive() {
            sout("Prepare hardDrive");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<String> data = new ArrayList<>();
        final Object monitor = "monitor";

        Consumer writer = new Consumer(data, monitor);
        writer.start();
/*
        while (!writer.isStarted()){
        }
*/
        Thread.sleep(2000);
        Producer reader = new Producer(data, monitor);
        reader.start();

    }

    private static void sout(String value) {
        System.out.println("[" + Thread.currentThread().getName() + "] " + value);
    }
}

