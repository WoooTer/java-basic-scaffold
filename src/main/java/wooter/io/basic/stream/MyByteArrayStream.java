package wooter.io.basic.stream;

import java.io.*;

/**
 * [Java8 I/O源码-ByteArrayInputStream](https://blog.csdn.net/panweiwei1994/article/details/78145143)
 * [Java8 I/O源码-ByteArrayOutputStream](https://blog.csdn.net/panweiwei1994/article/details/78209455)
 */
public class MyByteArrayStream {

    private final String originalTxt = "abc123";
    private InputStream input;
    private OutputStream output;

    public void read() throws IOException {
        int c;
        while ((c = input.read()) != -1) {
            output.write(c);
            // debug
            System.out.print((char)c);
        }
        output.flush();
    }

    public void readByte() throws IOException {
        byte[] data = new byte[128];
        while (input.read(data) != -1) {
            output.write(data);
        }
        // debug
        System.out.print(new String(data));
        output.flush();
    }

    public void readByteOff() throws IOException {
        byte[] data = new byte[128];
        while (input.read(data, 0, data.length) != -1) {
            output.write(data, 0, data.length);
        }
        // debug
        System.out.print(new String(data));
        output.flush();
    }

    public void open(){
        this.input = new ByteArrayInputStream(originalTxt.getBytes());
        this.output = new ByteArrayOutputStream();
    }

    public void useBuffer(){
        this.input = new BufferedInputStream(this.input);
        this.output = new BufferedOutputStream(this.output);
    }

    public static void main(String[] args) throws Exception {
        MyByteArrayStream my = new MyByteArrayStream();
        my.open();
        my.useBuffer();
        my.read();
    }
}
