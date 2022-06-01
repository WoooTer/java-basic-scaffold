package wooter.annotation.spring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Gzip {

    /**
     * 是否嵌套 默认：不嵌套
     * 
     * @return 是否嵌套，true：嵌套；false：不嵌套
     */
    boolean nesting() default false;
}
