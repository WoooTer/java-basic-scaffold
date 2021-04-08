package wooter.redis.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://spring.io/projects/spring-data-redis#learn
 */
public class Main {

    static ApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
    static RedisTemplate<String, Object> template = ctx.getBean(RedisTemplate.class);

    public static void main(String[] args) {
        set_add();
    }

    public static void value_setString() {
        template.opsForValue().set("spring", "abcd");
        String r = (String) template.opsForValue().get("spring");
        System.out.println(r);
    }

    public static void value_setObj() {
        template.opsForValue().set("spring", new Person().setName("wooter").setAge(18));
        Person r = (Person) template.opsForValue().get("spring");
        System.out.println(r);
    }

    public static void hash_set() {
        template.opsForHash().put("spring", "name", "wooter");
        template.opsForHash().put("spring", "age", 18);

        Map<Object, Object> r = template.opsForHash().entries("spring2");
        System.out.println(r);
    }

    public static void list_push() {
        template.opsForList().rightPush("spring", 1);
        template.opsForList().rightPush("spring", 2);

        List<Object> r = template.opsForList().range("spring", 0, -1);
        System.out.println(r);
    }

    public static void set_add() {
        template.opsForSet().add("spring", 1);
        template.opsForSet().add("spring", "2");

        Set<Object> r = template.opsForSet().members("spring");
        System.out.println(r);
    }

    public static void zset_add() {
        template.opsForZSet().add("spring", "a", 1);
        template.opsForZSet().add("spring", "b", 2);

        Set<Object> r = template.opsForZSet().range("spring", 0, 2);
        System.out.println(r);
    }
}
