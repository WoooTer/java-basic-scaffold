package wooter.design_pattern.proxy.static_;

import wooter.design_pattern.proxy.RealSubject;
import wooter.design_pattern.proxy.Subject;

/**
 * [代理模式（代理设计模式）详解](http://c.biancheng.net/view/1359.html)
 */
public class Main {

    public static void main(String[] args) {
        Subject subject = new ProxySubject(new RealSubject());
        subject.Request();
    }
}
