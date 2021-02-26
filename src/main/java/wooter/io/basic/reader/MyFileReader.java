package wooter.io.basic.reader;

import java.io.*;

/**
 * [Java8 I/O源码-FileReader和FileWriter](https://blog.csdn.net/panweiwei1994/article/details/78370341)
 * [Basic I/O - Character Streams](https://docs.oracle.com/javase/tutorial/essential/io/charstreams.html)
 */
public class MyFileReader {

    private Reader input;
    private Writer output;

    public void read() throws IOException {
        int c;
        while ((c = input.read()) != -1) {
            output.write(c);
        }
        output.flush();
    }

    public void readByte() throws IOException {
        char[] data = new char[128];
        while (input.read(data) != -1) {
            output.write(data);
        }
        output.flush();
    }

    public void readByteOff() throws IOException {
        char[] data = new char[128];
        while (input.read(data, 0, data.length) != -1) {
            output.write(data, 0, data.length);
        }
        output.flush();
    }

    public void open() throws IOException {
        this.input = new FileReader(".gitignore");
        this.output = new FileWriter("targetFile.tmp");
    }

    public void useBuffer(){
        this.input = new BufferedReader(this.input);
        this.output = new BufferedWriter(this.output);
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

    public static void main(String[] args) throws IOException {
        MyFileReader my = new MyFileReader();
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
