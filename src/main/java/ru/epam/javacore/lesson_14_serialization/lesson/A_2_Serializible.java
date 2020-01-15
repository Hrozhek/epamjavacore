package ru.epam.javacore.lesson_14_serialization.lesson;

import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;

public class A_2_Serializible {
    private static class Person implements Serializable {
        private String name;
        private String lastName;

        public Person(String name, String lastName) {
            this.name = name;
            this.lastName = lastName;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
