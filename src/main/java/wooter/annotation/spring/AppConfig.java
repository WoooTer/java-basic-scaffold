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

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = AppConfig.class)
public class AppConfig {

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(wooter.annotation.spring.AppConfig.class);
        PersonCall personCall = ctx.getBean(PersonCall.class);

        Address address0 = new Address().setCityId(110100).setCityName("北京");
        Address address1 = new Address().setCityId(120100).setCityName("天津");
        Address address2 = new Address().setCityId(130100).setCityName("石家庄");
        Address address3 = new Address().setCityId(130200).setCityName("唐山");
        Address address4 = new Address().setCityId(130300).setCityName("秦皇岛");
        Address[] addressArray = {address1, address2};
        List<Address> addressList = Arrays.asList(address3, address4);

        String[] titleArray = new String[] {"作家", "码农"};
        Person person = (Person)new Person().setId(1).setName("小明").setAddress(address0).setAddressArray(addressArray)
            .setAddressList(addressList).setTitleArray(titleArray).setCategory("人类");

        personCall.save(person);
        Person result = personCall.query();
        System.out.println(JSON.toJSONString(result));
    }

}
