package wooter.io_learning.io.stream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * [Java8 I/O源码-PipedInputStream与PipedOutputStream](https://blog.csdn.net/panweiwei1994/article/details/78212564)
 */
public class MyPipedStream {

    public static void main(String[] args) {
        Sender sender = new Sender();
        Receiver receiver = new Receiver();

        PipedOutputStream out = sender.getOutputStream();
        PipedInputStream in = receiver.getInputStream();

        try {
            // 连接输入流和输出流。下面两条语句的效果是一样。
            // out.connect(in);
            in.connect(out);

            sender.start();
            receiver.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Sender extends Thread {

    private PipedOutputStream out = new PipedOutputStream();

    public PipedOutputStream getOutputStream() {
        return out;
    }

    @Override
    public void run() {
        writeMessage();
    }

    // 向管道输出流中写入信息
    private void writeMessage() {
        String strInfo = "Hello World!";
        try {
            // 向管道输入流中写入数据
            out.write(strInfo.getBytes());
            // 释放资源
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Receiver extends Thread {
    private PipedInputStream in = new PipedInputStream();

    public PipedInputStream getInputStream() {
        return in;
    }

    @Override
    public void run() {
        readMessage();
    }

    // 从管道输入流中读取数据
    public void readMessage() {
        byte[] buf = new byte[1024];
        try {
            //从缓冲区中读取数据到buf中
            int len = in.read(buf);
            //打印读取到的内容
            System.out.println(new String(buf, 0, len));
            //释放资源
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
