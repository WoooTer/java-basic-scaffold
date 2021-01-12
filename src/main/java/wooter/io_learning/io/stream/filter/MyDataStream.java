package wooter.io_learning.io.stream.filter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * [Java8 I/O源码-DataInputStream与DataOutputStream](https://blog.csdn.net/panweiwei1994/article/details/78266266)
 * [Jenkov - Java DataInputStream](http://tutorials.jenkov.com/java-io/datainputstream.html)
 */
public class MyDataStream {

    public static void main(String[] args) throws Exception {
        DataOutputStream dataOutputStream =
                new DataOutputStream(
                        new FileOutputStream(".data-stream"));

        dataOutputStream.writeInt(123);
        dataOutputStream.writeFloat(123.45F);
        dataOutputStream.writeLong(789);

        dataOutputStream.close();

        DataInputStream dataInputStream =
                new DataInputStream(
                        new FileInputStream(".data-stream"));

        int   int123     = dataInputStream.readInt();
        float float12345 = dataInputStream.readFloat();
        long  long789    = dataInputStream.readLong();

        dataInputStream.close();

        System.out.println("int123     = " + int123);
        System.out.println("float12345 = " + float12345);
        System.out.println("long789    = " + long789);
    }
}
