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

        /**
         * SeekableByteChannel is an
         * interface that describes a channel that can be used for file operations. It is
         * implemented by the FileChannel class. When the default file system is used, the
         * returned object can be cast to FileChannel.
         */
        try (SeekableByteChannel channel = Files.newByteChannel(file);) {
            ByteBuffer buffer = ByteBuffer.allocate(128);
            int numberOfRead = -1;

            while ((numberOfRead = channel.read(buffer)) != -1) {
                //Rewind buffer so it can be read
                /**
                 * his call is
                 * necessary because the current position is at the end of the buffer after the call to
                 * read( ). It must be reset to the start of the buffer in order for the bytes in mBuf to
                 * be read by calling get( ). (Recall that get( ) is defined by ByteBuffer.) Because
                 * mBuf is a byte buffer, the values returned by get( ) are bytes. They are cast to
                 * char so the file can be displayed as text.
                 */
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
                /**
                 * It is useful to emphasize an important aspect of this program. As mentioned,
                 * after data is written to mBuf, but before it is written to the file, a call to rewind(
                 * ) on mBuf is made. This is necessary in order to reset the current position to zero
                 * after data has been written to mBuf. Remember, each call to put( ) on mBuf
                 * advances the current position. Therefore, it is necessary for the current position
                 *to be reset to the start of the buffer before calling write( ). If this is not done,
                 * write( ) will think that there is no data in the buffer
                 *
                 *
                 * for example we can put some extra data here : buffer.put()
                 *
                 *
                 * Another way to handle the resetting of the buffer between input and output
                 * operations is to call flip( ) instead of rewind( ). The flip( ) method sets the value
                 * of the current position to zero and the limit to the previous current position. In
                 * the preceding example, because the capacity of the buffer equals its limit, flip( )
                 * could have been used instead of rewind( ). However, the two methods are not
                 * interchangeable in all cases.
                 * In general, you must reset the buffer between read and write operations.
                 */
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
