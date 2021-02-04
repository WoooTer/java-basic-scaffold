package wooter.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * http://tutorials.jenkov.com/java-reflection/private-fields-and-methods.html
 */
public class ReflectPrivate {

    public static void main(String[] args) throws Exception {
        accessPrivateMethod();
    }

    public static void accessPrivateField() throws Exception {
        MyReflectBean privateObject = new MyReflectBean("The Private Value");

        Field privateStringField = MyReflectBean.class.getDeclaredField("stringAttr");
        privateStringField.setAccessible(true);

        String fieldValue = (String) privateStringField.get(privateObject);
        System.out.println("fieldValue = " + fieldValue);
    }

    public static void accessPrivateMethod() throws Exception {
        MyReflectBean privateObject = new MyReflectBean("The Private Value");

        Method privateMethod = MyReflectBean.class.getDeclaredMethod("accessStringAttr", null);
        privateMethod.setAccessible(true);

        String returnValue = (String) privateMethod.invoke(privateObject, null);
        System.out.println("returnValue = " + returnValue);
    }

}
