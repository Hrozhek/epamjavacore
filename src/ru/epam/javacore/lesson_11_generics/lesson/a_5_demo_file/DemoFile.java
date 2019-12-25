package ru.epam.javacore.lesson_11_generics.lesson.a_5_demo_file;

import java.io.File;

public class DemoFile {


    public static void main(String[] args) {
        FileService fileService = new FileIoService();
        fileService.demoReadFile();
    }
}
