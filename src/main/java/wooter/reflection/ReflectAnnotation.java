package wooter.reflection;

import java.lang.annotation.Annotation;

/**
 * http://tutorials.jenkov.com/java-reflection/annotations.html
 */
public class ReflectAnnotation {

    public static void main(String[] args) {
        Class aClass = MyReflectBean.class;
        Annotation annotation = aClass.getAnnotation(MyReflectAnnotation.class);

        if(annotation instanceof MyReflectAnnotation){
            MyReflectAnnotation myAnnotation = (MyReflectAnnotation) annotation;
            System.out.println("name: " + myAnnotation.name());
            System.out.println("value: " + myAnnotation.value());
        }
    }

}
