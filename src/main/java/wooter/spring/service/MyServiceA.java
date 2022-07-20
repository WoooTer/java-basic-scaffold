package wooter.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wooter.spring.annotation.MyWarpper;

@Component
public class MyServiceA {

    @Autowired
    @MyWarpper
    private MyServiceB myServiceB;

    private String name = "my-service-A";

    public void printName(){
        System.out.println(this.name);
    }

    public void printInnerName() {
        myServiceB.printName();
    }
}
