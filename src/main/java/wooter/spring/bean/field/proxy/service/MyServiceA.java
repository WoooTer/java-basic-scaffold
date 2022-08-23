package wooter.spring.bean.field.proxy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wooter.spring.bean.field.proxy.annotation.MyBeanFieldProxy;

@Component
public class MyServiceA {

    @Autowired
    @MyBeanFieldProxy
    private MyServiceB myServiceB;

    private String name = "my-service-A";

    public void printName(){
        System.out.println(this.name);
    }

    public void printInnerName() {
        myServiceB.printName();
    }
}
