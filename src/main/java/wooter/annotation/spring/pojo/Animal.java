package wooter.annotation.spring.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import wooter.annotation.spring.Gzip;

@Data
@Accessors(chain = true)
public class Animal {

    @Gzip
    private String category;
}
