package ru.epam.javacore.lesson_14_.lesson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class A_10_ClassRefactorSolution {
    private static class Person implements Serializable {
        private static final long serialVersionUID = 189323L;
        public static void main(String[] args) {

        }
    }
    private static class Person55 implements Serializable {
        private static final long serialVersionUID = 189323L;
      //  private String name;
      //  private String lastName;

        public Person55(String name, String lastName) {
          //  this.name = name;
         //   this.lastName = lastName;
        }


        public static void main(String[] args) throws Exception {
          //  writerToFile();
              readFromFile();
        }

        private static void writerToFile() {
            Path file = null;
            try {
                file = Files.createTempFile("lesson14", ".txt");

                try (ObjectOutput objectOutput = new ObjectOutputStream(
                        new FileOutputStream(file.toFile()))) {

                    objectOutput.writeObject(new Person55("Ivan", "Ivanov"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(file);
        }

        private static void readFromFile() throws Exception {
            String file = "C:\\Users\\student\\AppData\\Local\\Temp\\lesson141720321055230113113.txt";
            try (ObjectInputStream stream = new ObjectInputStream(
                    new FileInputStream(new File(file)))) {

                Object o = stream.readObject();
                Person person = (Person) o;
                System.out.println();
            }
        }
    }
}
