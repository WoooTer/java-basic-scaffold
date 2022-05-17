package wooter.annotation.spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Compress
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private Long id;

    @Compress
    private String name;
}
