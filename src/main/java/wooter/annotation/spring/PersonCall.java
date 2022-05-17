package wooter.annotation.spring;

import org.springframework.stereotype.Component;

@Component
public class PersonCall {

    @Gzip
    public void save(Person person) {
        System.out.println(person.getName());
    }

    @Gzip
    public Person query() {
        return new Person(1, "H4sIAAAAAAAAAHu6of/ZjD4AzIdBTAYAAAA=");
    }
}
