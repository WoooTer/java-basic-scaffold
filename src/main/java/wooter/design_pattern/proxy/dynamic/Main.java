package wooter.design_pattern.proxy.dynamic;

import wooter.design_pattern.proxy.RealSubject;
import wooter.design_pattern.proxy.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * [java动态代理实现与原理详细分析](https://www.cnblogs.com/gonjan-blog/p/6685611.html)
 */
public class Main {

    public static void main(String[] args) {
        hasImplementation();
        hasNoImplementation();
    }

    /**
     * 对实现了接口的类，进行代理
     */
    public static void hasImplementation() {
        InvocationHandler handler = new SubjectInvocationHandler<Subject>(new RealSubject());

        Subject subject =
            (Subject)Proxy.newProxyInstance(Main.class.getClassLoader(), new Class<?>[] {Subject.class}, handler);

        subject.Request();
    }

    /**
     * 不存在实现该接口的类，直接对接口进行代理
     */
    public static void hasNoImplementation() {
        InvocationHandler handler = (Object proxy, Method method, Object[] args) -> {
            System.out.println("无中生有");
            return null;
        };

        Subject subject =
            (Subject)Proxy.newProxyInstance(Main.class.getClassLoader(), new Class<?>[] {Subject.class}, handler);
        subject.Request();
    }
}
