package wooter.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * http://tutorials.jenkov.com/java-reflection/classes.html
 */
public class ReflectClass {

    public static void main(String[] args) {
        Class<MyReflectBean> aClass = MyReflectBean.class;

        String className = aClass.getName();

        String simpleClassName = aClass.getSimpleName();

        int modifiers = aClass.getModifiers();

        Package aPackage = aClass.getPackage();

        Class superclass = aClass.getSuperclass();

        Class[] interfaces = aClass.getInterfaces();

        Constructor[] constructors = aClass.getConstructors();

        Method[] methods = aClass.getMethods();

        Field[] fields = aClass.getFields();

        Annotation[] annotations = aClass.getAnnotations();
    }
}
