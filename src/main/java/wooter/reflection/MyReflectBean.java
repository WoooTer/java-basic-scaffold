package wooter.reflection;

@MyReflectAnnotation(name = "myName", value = "myValue")
public class MyReflectBean {

    private String stringAttr;

    public MyReflectBean() {
    }

    public MyReflectBean(String stringAttr) {
        this.stringAttr = stringAttr;
    }

    private String accessStringAttr() {
        return this.stringAttr;
    }

}
