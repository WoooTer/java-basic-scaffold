package wooter.annotation.spring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wooter.annotation.spring.Gzip;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private Integer cityId;

    @Gzip
    private String cityName;
}
