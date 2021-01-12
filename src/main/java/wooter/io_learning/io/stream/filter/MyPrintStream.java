package wooter.io_learning.io.stream.filter;

import java.io.*;

public class MyPrintStream {

    public static void main(String[] args) throws IOException{

        try(PrintStream printStream = new PrintStream(System.out)){
            printStream.println(123);
            printStream.println(123.456);
            printStream.printf("Text + data: %s\n", "abc");
        }
    }
}
