package wooter.annotation.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = AppConfig.class)
public class AppConfig {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(wooter.annotation.spring.AppConfig.class);
        PersonCall personCall = ctx.getBean(PersonCall.class);

        Person param = new Person(1, "小明");
        personCall.save(param);

        Person returnParam = personCall.query();
        System.out.println(returnParam.getName());
    }

}
