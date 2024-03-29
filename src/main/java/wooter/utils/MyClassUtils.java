package wooter.utils;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class MyClassUtils {

    /**
     * 判断是否为基本类型、基本类型数组
     * 
     * @param clazz
     * @return
     */
    public static boolean isSimpleProperty(Class<?> clazz) {
        if (clazz == null) {
            return false;
        } else {
            return isSimpleValueType(clazz) || isSimpleValueTypeArray(clazz);
        }
    }

    /**
     * 判断是否为基本类型
     * 
     * @param type
     * @return
     */
    public static boolean isSimpleValueType(Class<?> type) {
        return Void.class != type && Void.TYPE != type
            && (ClassUtils.isPrimitiveOrWrapper(type) || Enum.class.isAssignableFrom(type)
                || CharSequence.class.isAssignableFrom(type) || Number.class.isAssignableFrom(type)
                || Date.class.isAssignableFrom(type) || URI.class == type || URL.class == type || Locale.class == type
                || Class.class == type);
    }

    /**
     * 判断是否为基本类型数组
     * 
     * @param clazz
     * @return
     */
    public static boolean isSimpleValueTypeArray(Class<?> clazz) {
        return clazz.isArray() && isSimpleValueType(clazz.getComponentType());
    }

    /**
     * https://stackoverflow.com/questions/35609456/how-to-get-values-of-nested-class-attribute-using-reflection
     * 
     * @param root
     * @param inspected
     * @param annotationClazz
     * @return
     * @throws ReflectiveOperationException
     */
    public static List<Pair<Field, Object>> getAnnotatedValues(final Object root, final Set<Object> inspected,
        Class<? extends Annotation> annotationClazz) throws ReflectiveOperationException {
        final List<Pair<Field, Object>> annotatedValues = new ArrayList<>();
        // Prevents stack overflow.
        if (inspected.contains(root)) {
            return Collections.EMPTY_LIST;
        }
        inspected.add(root);
        for (final Field field : FieldUtils.getAllFieldsList(root.getClass())) {
            field.setAccessible(true);
            final Object currentValue = field.get(root);
            field.setAccessible(false);
            if (field.isAnnotationPresent(annotationClazz)) {
                // Found required value, search finished:
                annotatedValues.add(Pair.of(field, root));
                if (currentValue != null) {
                    inspected.add(currentValue);
                }
            } else if (currentValue != null && !isSimpleValueType(currentValue.getClass())) {
                // Searching for annotated fields in nested classes:
                annotatedValues.addAll(getAnnotatedValues(currentValue, inspected, annotationClazz));
            }
        }
        return annotatedValues;
    }
}
