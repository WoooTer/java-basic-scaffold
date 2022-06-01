package wooter.design_pattern.proxy.cglib;

import wooter.design_pattern.proxy.RealSubject;

public class Main {

    public static void main(String[] args) {
        ProxyFactory<RealSubject> proxyFactory = new ProxyFactory<>(new RealSubject());
        RealSubject realSubject = proxyFactory.getProxyInstance();
        realSubject.Request();
    }
}
