package ru.epam.javacore.lesson_12_io_nio.lesson.a_2_nio;

import ru.epam.javacore.lesson_12_io_nio.lesson.a_0_common.FileService;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class FileNioService implements FileService {
    private static final String FILE_PATH = "C:\\Users\\student\\Desktop\\dir\\1.txt";
    private static final String DIR_PATH = "C:\\Users\\student\\Desktop";

    @Override
    public void demoCreateFile() {
        Path file = Paths.get(FILE_PATH);
        System.out.println(file.getRoot());
        System.out.println(file.getParent());
        System.out.println(file.getFileName());
        System.out.println(file.getNameCount());
    }

    @Override
    public void demoListFiles() {
        Path dir = Paths.get(DIR_PATH);
        try {
            Stream<Path> walk = Files.walk(dir, 1);
            walk.forEach(new Consumer<Path>() {
                @Override
                public void accept(Path path) {
                    System.out.println(path);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void demoReadFile() {
        //demoReadWithChannels();
        demoSimpleReadFile();
    }

    private void demoReadWithChannels() {
        Path file = Paths.get(FILE_PATH);

        StringBuilder stringBuilder = new StringBuilder();

        try (SeekableByteChannel channel = Files.newByteChannel(file);) {
            ByteBuffer buffer = ByteBuffer.allocate(128);
            int numberOfRead = -1;

            while ((numberOfRead = channel.read(buffer)) != -1) {
                buffer.rewind();
                for (int i = 0; i < numberOfRead; i++) {
                    stringBuilder.append((char) buffer.get(i));
                }
            }

            System.out.println(stringBuilder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void demoSimpleReadFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void demoWriterFile() {

    }

    @Override
    public void demoWriteBinaryFile() {
        String src = "C:\\Users\\student\\Desktop\\dir\\dir.rar";
        String dest = "C:\\Users\\student\\Desktop\\dir\\dir_copy.tt800";

        try (
                SeekableByteChannel in = Files.newByteChannel(Paths.get(src));
                FileChannel out = (FileChannel)
                        Files.newByteChannel(Paths.get(dest),
                                             StandardOpenOption.WRITE,
                                             StandardOpenOption.CREATE);
        ) {

            ByteBuffer byteBuffer = ByteBuffer.allocate(128);
            int read = -1;
            while ((read = in.read(byteBuffer)) != -1) {
                //  System.out.println(read);
                //todo ERROR
                //byteBuffer.rewind();
                //out.write(byteBuffer);

                byteBuffer.flip();
                out.write(byteBuffer);
                byteBuffer.compact();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
