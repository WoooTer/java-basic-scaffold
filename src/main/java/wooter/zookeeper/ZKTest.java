package wooter.zookeeper;

import java.nio.charset.StandardCharsets;

/**
 * [Getting Started with Java and Zookeeper](https://www.baeldung.com/java-zookeeper)
 */
public class ZKTest {

    public static void main(String[] args) throws Exception {
        ZKManager zkManager = new ZKManager();
        zkManager.create("/myzoo", "mydata".getBytes(StandardCharsets.UTF_8));
        zkManager.update("/myzoo", "mydata2".getBytes(StandardCharsets.UTF_8));
        String result = zkManager.getZNodeData("/myzoo", true);
        System.out.println(result);
    }
}
