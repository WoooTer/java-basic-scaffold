package wooter.spring.service;

import org.springframework.stereotype.Component;

@Component
public class MyServiceB {

    private String name = "my-service-B";

    public void printName(){
        System.out.println(this.name);
    }
}
