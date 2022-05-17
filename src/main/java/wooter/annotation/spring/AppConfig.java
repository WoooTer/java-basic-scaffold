package wooter.annotation.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = PersonCall.class)
public class AppConfig {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(wooter.annotation.spring.AppConfig.class);
        PersonCall personCall = ctx.getBean(PersonCall.class);
        personCall.call(null, null);
    }

}
