package wooter.io_learning.io.stream;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class StreamTest {

    public void byteArrayInputStream2outputStream() throws IOException {
        String originalString = "sdasdgwerwerewrwer";
        InputStream inputStream = new ByteArrayInputStream(originalString.getBytes());

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();
        byte[] byteArray = buffer.toByteArray();

        String text = new String(byteArray, StandardCharsets.UTF_8);
        System.out.println(text);
    }

    public void fileInputStream2outputStream_atOnce() throws IOException {
        InputStream initialStream = new FileInputStream(new File(".gitignore"));
        byte[] buffer = new byte[initialStream.available()];
        initialStream.read(buffer);

        File targetFile = new File("targetFile.tmp");
        OutputStream outStream = new FileOutputStream(targetFile);
        outStream.write(buffer);
    }

    public void fileInputStream2outputStream_InProgress() throws IOException {
        InputStream initialStream = new FileInputStream(new File(".gitignore"));
        File targetFile = new File("targetFile.tmp");
        OutputStream outStream = new FileOutputStream(targetFile);

        byte[] buffer = new byte[8 * 1024];
        int bytesRead;
        while ((bytesRead = initialStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
    }

    public static void main(String[] args) throws Exception {
        StreamTest test = new StreamTest();
        test.fileInputStream2outputStream_InProgress();
    }
}
