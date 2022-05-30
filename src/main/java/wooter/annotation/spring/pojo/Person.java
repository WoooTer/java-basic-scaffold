package wooter.annotation.spring.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import wooter.annotation.spring.Gzip;

import java.util.List;

@Data
@Accessors(chain = true)
public class Person extends Animal {

    private Integer id;

    @Gzip
    private String name;

    @Gzip
    private String[] titleArray;

    private Address address;

    private Address[] addressArray;

    private List<Address> addressList;
}
