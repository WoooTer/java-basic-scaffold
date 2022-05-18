package wooter.annotation.spring;

import com.alibaba.fastjson.JSON;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import wooter.annotation.spring.pojo.Address;
import wooter.annotation.spring.pojo.Person;
import wooter.annotation.spring.service.PersonCall;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = AppConfig.class)
public class AppConfig {

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(wooter.annotation.spring.AppConfig.class);
        PersonCall personCall = ctx.getBean(PersonCall.class);

        Person param = new Person(1, "小明", "人类", new Address(1, "北京"));
        personCall.save(param);

        Person returnParam = personCall.query();
        System.out.println(JSON.toJSONString(returnParam));
    }

}
