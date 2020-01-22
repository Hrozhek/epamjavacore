package ru.epam.javacore.lesson_17_concurrency.lesson.a_2_;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class A_1_TT {
    private static class DataApplierSet extends Thread {
        private Set<String> storage;
        private int iterations;

        public DataApplierSet(Set<String> storage, int iterations) {
            this.storage = storage;
            this.iterations = iterations;
        }

        @Override
        public void run() {
            for (int i = 0; i < iterations; i++) {
                storage.add("[" + currentThread() + "]_" + i);
            }
        }
    }

    private static class DataApplier extends Thread {
        private List<String> storage;
        private int iterations;

        public DataApplier(List<String> storage, int iterations) {
            this.storage = storage;
            this.iterations = iterations;
        }

        @Override
        public void run() {
            for (int i = 0; i < iterations; i++) {
                storage.add("[" + currentThread() + "]_" + i);
            }
        }
    }

    private static class DataApplier2 extends Thread {
        private List<Integer> storage;
        private int iterations;

        public DataApplier2(List<Integer> storage, int iterations) {
            this.storage = storage;
            this.iterations = iterations;
        }

        @Override
        public void run() {
            for (int i = 0; i < iterations; i++) {
                if (!storage.contains(i)) {
                    //
                    storage.add(i);
                }
            }
        }
    }

    private static class DataApplierMap extends Thread {
        private Map<String, String> storage;
        private int iterations;

        public DataApplierMap(Map<String, String> storage, int iterations) {
            this.storage = storage;
            this.iterations = iterations;
        }

        @Override
        public void run() {
            for (int i = 0; i < iterations; i++) {
                storage.put("[" + currentThread() + "]_" + i, i + "");
            }
        }
    }

    public static void main(String[] args) {
        // demoCollection(new Vector<>(2));
        //demoCollection(Collections.synchronizedList(new ArrayList<>(2)));
        demoCollection(new CopyOnWriteArrayList<>());

        // demoMap(new ConcurrentHashMap<>());
        // long time = System.currentTimeMillis();
        //demoSet(Collections.synchronizedSet(new HashSet<>()));
        //demoSet(new CopyOnWriteArraySet<>());
        // System.out.println("Time " + (System.currentTimeMillis() - time));

        // demoCollection2(Collections.synchronizedList(new ArrayList<>(2)));
    }

    private static void demoCollection(List<String> data) {
        Thread thread = new DataApplier(data, 30000);
        thread.start();

        Thread thread2 = new DataApplier(data, 70000);
        thread2.start();

        try {
            thread.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(data.size());
    }

    private static void demoCollection2(List<Integer> data) {
        new DataApplier2(data, 1000).start();
        new DataApplier2(data, 1000).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(data.size());
    }

    private static void demoMap(Map<String, String> data) {
        new DataApplierMap(data, 30000).start();
        new DataApplierMap(data, 70000).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(data.size());
    }

    private static void demoSet(Set<String> data) {
        new DataApplierSet(data, 30000).start();
        new DataApplierSet(data, 70000).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(data.size());
    }
}
