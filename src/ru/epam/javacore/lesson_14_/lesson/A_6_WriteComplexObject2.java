package ru.epam.javacore.lesson_14_.lesson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class A_6_WriteComplexObject2 {
    private static class Animal implements Serializable {

        private String kind;
        private transient int weight;
        private transient Country country;

        public Animal(String kind, int weight, Country country) {
            this.kind = kind;
            this.weight = weight;
            this.country = country;
        }

        @Override
        public String toString() {
            return "Animal{" +
                    "kind='" + kind + '\'' +
                    ", weight=" + weight +
                    ", country=" + country +
                    '}';
        }
    }

    private static class Country implements Serializable {
        private String name;

        public Country(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Country{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        Path file = null;
        try {
            file = Files.createTempFile("lesson14", ".txt");

            try (ObjectOutput objectOutput = new ObjectOutputStream(
                    new FileOutputStream(file.toFile()))) {
                Animal a = new Animal(
                        "Tiger", 300, new Country("Russia")
                );
                objectOutput.writeObject(a);
            }

            Animal animal = readAnimalFromFile(file.toFile().getAbsolutePath());
            System.out.println(animal);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                    System.out.println(file);
                    System.out.println(file.getFileName());
                    //   Files.delete(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println();
    }

    private static Animal readAnimalFromFile(String file) throws Exception {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            Object o = inputStream.readObject();
            return (Animal) o;
        }
    }
}
