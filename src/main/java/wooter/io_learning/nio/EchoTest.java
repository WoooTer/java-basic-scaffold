package wooter.io_learning.nio;

import java.io.IOException;

public class EchoTest {

    Process server;
    EchoClient client;

    public void setup() throws IOException, InterruptedException {
//        server = EchoServer.start();
        client = EchoClient.start();
    }

    public void givenServerClient_whenServerEchosMessage_thenCorrect() {
        String resp1 = client.sendMessage("hello");
        String resp2 = client.sendMessage("world");
    }

    public void teardown() throws IOException {
//        server.destroy();
        EchoClient.stop();
    }

    public static void main(String[] args) throws Exception {
        EchoTest test = new EchoTest();
        test.setup();
        test.givenServerClient_whenServerEchosMessage_thenCorrect();
        test.teardown();

    }
}
