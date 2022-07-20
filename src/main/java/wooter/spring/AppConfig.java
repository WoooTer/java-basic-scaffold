package wooter.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import wooter.spring.service.MyServiceA;

/**
 * [Spring Java Config](https://docs.spring.io/spring-framework/docs/5.3.3/reference/html/core.html#beans-java)
 * [Spring startup steps](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#application-startup-steps)
 */
@Configuration
@ComponentScan(basePackageClasses = AppConfig.class)
public class AppConfig {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        MyServiceA myServiceA = ctx.getBean(MyServiceA.class);
        myServiceA.printName();
        myServiceA.printInnerName();
    }

}
