package wooter.io.basic.stream;

import java.io.*;

/**
 * [Java8 I/O源码-FileInputStream与FileOutputStream](https://blog.csdn.net/panweiwei1994/article/details/78277714)
 */
public class MyFileStream {

    private InputStream input;
    private OutputStream output;

    public void read() throws IOException {
        int c;
        while ((c = input.read()) != -1) {
            output.write(c);
        }
        output.flush();
    }

    public void readByte() throws IOException {
        byte[] data = new byte[input.available()];
        while (input.read(data) != -1) {
            output.write(data);
        }
        output.flush();
    }

    public void readByteOff() throws IOException {
        byte[] data = new byte[input.available()];
        while (input.read(data, 0, data.length) != -1) {
            output.write(data, 0, data.length);
        }
        output.flush();
    }

    public void open() throws FileNotFoundException{
        this.input = new FileInputStream(new File(".gitignore"));
        this.output = new FileOutputStream(new File("targetFile.tmp"));
    }

    public void useBuffer(){
        this.input = new BufferedInputStream(this.input);
        this.output = new BufferedOutputStream(this.output);
    }

    public void close() {
        try {
            if (input != null) {
                input.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (output != null) {
                output.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyFileStream my = new MyFileStream();
        try {
            my.open();
            my.useBuffer();
            my.read();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            my.close();
        }
    }
}
