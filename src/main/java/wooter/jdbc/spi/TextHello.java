package wooter.jdbc.spi;

public class TextHello implements HelloSPI {

    public void sayHello() {
        System.out.println("Text Hello");
    }
}
