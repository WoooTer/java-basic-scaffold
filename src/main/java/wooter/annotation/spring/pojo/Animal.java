package wooter.annotation.spring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wooter.annotation.spring.Gzip;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    @Gzip
    private String category;
}
