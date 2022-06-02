package wooter.agent;

/**
 * https://zhuanlan.zhihu.com/p/139756708
 */
public class Main {

    /**
     * VM options: -javaagent:D:\codeWooter\java-basic-scaffold\target\wooter-1.0-SNAPSHOT.jar
     * 
     * @param args
     */
    public static void main(String[] args) {
        User user = new User("zane");
        System.out.println(user.toString());
    }
}
