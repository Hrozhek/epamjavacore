package ru.epam.javacore.lesson_12_io_nio.lesson.a_2_nio;

public class NioDemo {
    public static void main(String[] args) {
        FileNioService fileNioService = new FileNioService();
        //fileNioService.demoCreateFile();
       // fileNioService.demoListFiles();
       // fileNioService.demoReadFile();
        fileNioService.demoWriteBinaryFile();
    }
}
