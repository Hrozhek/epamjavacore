package ru.epam.javacore.lesson_14_serialization.lesson;



import ru.epam.javacore.lesson_14_serialization.lesson.a_8_singleton_solution.Sun;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class A_8_SingletonSolution {
    public static void main(String[] args) {
        Path file = null;
        try {
            file = Files.createTempFile("lesson14", ".txt");

            try (ObjectOutput objectOutput = new ObjectOutputStream(
                    new FileOutputStream(file.toFile()))) {

                System.out.println(Sun.INSTANCE);
                objectOutput.writeObject(Sun.INSTANCE);
            }

            System.out.println("---After file read singleton-----");
            Sun sun = readSunFromFile(file.toFile().getAbsolutePath());
            System.out.println(sun);
            System.out.println(Sun.INSTANCE);

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
    }

    private static Sun readSunFromFile(String file) throws Exception {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            Object o = inputStream.readObject();
            return (Sun) o;
        }
    }
}
