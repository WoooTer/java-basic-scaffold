package wooter.spring.startup;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import wooter.spring.service.MyService;

/**
 * [Spring Java Config](https://docs.spring.io/spring-framework/docs/5.3.3/reference/html/core.html#beans-java)
 * [Spring startup steps](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#application-startup-steps)
 */
@Configuration
@ComponentScan(basePackageClasses = MyService.class)
public class AppConfig {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        MyService myService = ctx.getBean(MyService.class);
        myService.printName();
    }

}
