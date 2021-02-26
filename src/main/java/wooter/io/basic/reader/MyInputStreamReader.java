package wooter.io.basic.reader;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * [Java8 I/O源码-InputStreamReader与OutputStreamWriter](https://blog.csdn.net/panweiwei1994/article/details/78398769)
 * [JDK1.8中的try-with-resources声明](https://blog.csdn.net/weixin_40255793/article/details/80812961)
 */
public class MyInputStreamReader {

    public static void main(String[] args) {
        // try-with-resources
        try (
                InputStream inputStream = new FileInputStream(".gitignore");
                OutputStream outputStream = new FileOutputStream("targetFile.tmp");

                Reader inputReader = new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name()));
                Writer outputReader = new OutputStreamWriter(outputStream, Charset.forName(StandardCharsets.UTF_8.name()))
        ) {
            int c;
            while ((c = inputReader.read()) != -1) {
                outputReader.write(c);
                System.out.print((char) c);
            }
        } catch (Exception e) {
            e.printStackTrace();

            Throwable[] suppressed = e.getSuppressed();
            for (int i = 0; i < suppressed.length; i++) {
                System.out.println(suppressed[i].getMessage());
            }
        }

    }
}
