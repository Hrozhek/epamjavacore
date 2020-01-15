package ru.epam.javacore.lesson_14_serialization.lesson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class A_3_WriteReadObject {

    private static class Person implements Serializable {
        private String name;
        private String lastName;

        public Person(String name, String lastName) {
            this.name = name;
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        Path file = null;
        try {
            file = Files.createTempFile("lesson14", ".txt");

            try (ObjectOutput objectOutput = new ObjectOutputStream(
                    new FileOutputStream(file.toFile()))) {
                objectOutput.writeObject(new Person("Ivan", "Ivanov"));
            }

            Person person = readPersonFromFile(file.toFile().getAbsolutePath());
            System.out.println(person);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                 //   Files.delete(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println();
    }

    private static Person readPersonFromFile(String file) throws Exception {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            Object o = inputStream.readObject();
            return (Person) o;
        }
    }

}
