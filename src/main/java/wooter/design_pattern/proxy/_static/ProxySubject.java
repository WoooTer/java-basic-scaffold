package wooter.design_pattern.proxy._static;

import wooter.design_pattern.proxy.Subject;

/**
 * 静态代理
 */
public class ProxySubject implements Subject {

    private Subject realSubject;

    public ProxySubject(Subject subject) {
        this.realSubject = subject;
    }

    public void Request() {
        preRequest();
        realSubject.Request();
        postRequest();
    }

    public void preRequest() {
        System.out.println("访问真实主题之前的预处理。");
    }

    public void postRequest() {
        System.out.println("访问真实主题之后的后续处理。");
    }
}
