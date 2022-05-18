package wooter.annotation.spring.service;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import wooter.annotation.spring.Gzip;
import wooter.annotation.spring.pojo.Person;

@Component
public class PersonCall {

    private Person person;

    @Gzip
    public void save(Person person) {
        this.person = person;
        String jsonStr = JSON.toJSONString(person);
        System.out.println(jsonStr);
    }

    @Gzip
    public Person query() {
        return this.person;
    }
}
