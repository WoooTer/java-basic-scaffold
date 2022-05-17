package wooter.annotation.spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Gzip
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private Integer id;

    @Gzip
    private String name;
}
