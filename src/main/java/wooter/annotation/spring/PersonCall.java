package wooter.annotation.spring;

import org.springframework.stereotype.Component;

@Component
public class PersonCall {

    @Compress
    public Person call(Person person, Long id) {
        System.out.println("person call");
        return null;
    }
}
