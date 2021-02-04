package wooter.spring.service;

import org.springframework.stereotype.Component;

@Component
public class MyService {

    private String name = "my-service";

    public void printName(){
        System.out.println(this.name);
    }

}
