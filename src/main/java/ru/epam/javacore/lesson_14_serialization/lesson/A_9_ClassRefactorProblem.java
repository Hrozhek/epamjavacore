package ru.epam.javacore.lesson_14_serialization.lesson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class A_9_ClassRefactorProblem {

    private static class Person implements Serializable {
        private String name;
      //  private String lastName;

        public Person(String name, String lastName) {
            this.name = name;
         //   this.lastName = lastName;
        }


        public static void main(String[] args) throws Exception {
            // writerToFile();
            readFromFile();
        }

        private static void writerToFile() {
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
            System.out.println(file);
        }

        private static void readFromFile() throws Exception {
            String file = "C:\\Users\\student\\AppData\\Local\\Temp\\lesson14232039221898714035.txt";
            try (ObjectInputStream stream = new ObjectInputStream(
                    new FileInputStream(new File(file)))) {

                Object o = stream.readObject();
                Person person = (Person) o;
                System.out.println();
            }
        }
    }
}
