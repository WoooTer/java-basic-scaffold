package wooter.db.jdbc.spi;

import java.util.ServiceLoader;

/**
 * [通俗易懂的双亲委派机制](https://blog.csdn.net/codeyanbao/article/details/82875064)
 * [为什么说java spi破坏双亲委派模型？](https://www.zhihu.com/question/49667892/answer/690161827)
 *
 * [深入理解 Java 中 SPI 机制](https://mp.weixin.qq.com/s/vpy5DJ-hhn0iOyp747oL5A)
 * [Java SPI思想梳理—DriverManager spi案例](https://zhuanlan.zhihu.com/p/28909673)
 */
public class SPIDemo {

    public static void main(String[] args) {
        ServiceLoader<HelloSPI> serviceLoader = ServiceLoader.load(HelloSPI.class);
        // 执行不同厂商的业务实现，具体根据业务需求配置
        for (HelloSPI helloSPI : serviceLoader) {
            helloSPI.sayHello();
        }
    }
}
