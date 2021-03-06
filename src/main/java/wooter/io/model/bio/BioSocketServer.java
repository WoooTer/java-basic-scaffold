package wooter.io.model.bio;


import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 架构设计：系统间通信（3）——IO通信模型和JAVA实践 上篇
 * https://blog.csdn.net/yinwenjie/article/details/48472237
 *
 * 写一个迷你版的Tomcat
 * https://www.jianshu.com/p/dce1ee01fb90
 */
public class BioSocketServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(83);

        try {
            while (true) {
                //这里JAVA通过JNI请求操作系统，并一直等待操作系统返回结果（或者出错）
                Socket socket = serverSocket.accept();

                //下面我们收取信息（这里还是阻塞式的,一直等待，直到有数据可以接受）
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                Integer sourcePort = socket.getPort();
                int maxLen = 2048;
                byte[] contextBytes = new byte[maxLen];
                int realLen;
                StringBuffer message = new StringBuffer();
                //read的时候，程序也会被阻塞，直到操作系统把网络传来的数据准备好。
                while ((realLen = in.read(contextBytes, 0, maxLen)) != -1) {
                    message.append(new String(contextBytes, 0, realLen));
                    /*
                     * 我们假设读取到“over”关键字，
                     * 表示客户端的所有信息在经过若干次传送后，完成
                     * */
                    if (message.indexOf("over") != -1) {
                        break;
                    }
                }
                //下面打印信息
                System.out.println("服务器收到来自于端口：" + sourcePort + "的信息：" + message);

                //下面开始发送信息
                out.write("回发响应信息！".getBytes());

                //关闭
                out.close();
                in.close();
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }
}