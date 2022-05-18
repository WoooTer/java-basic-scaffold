package wooter.annotation.spring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wooter.annotation.spring.Gzip;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person extends Animal {

    private Integer id;

    @Gzip
    private String name;

    private Address address;

    public Person(Integer id, String name, String category, Address address) {
        super(category);
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
