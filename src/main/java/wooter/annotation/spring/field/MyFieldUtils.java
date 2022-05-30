package wooter.annotation.spring.field;

import org.apache.commons.lang3.reflect.FieldUtils;
import wooter.annotation.spring.Gzip;
import wooter.annotation.spring.pojo.Person;
import wooter.utils.MyClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class MyFieldUtils {

    private static volatile Map<Class<?>, List<FieldTreeNode>> CLASS_FIELD_MARKED_PATH_CACHE = new HashMap<>();

    /**
     * 获取一个类所有的 field
     * 
     * @param root
     * @return
     */
    public static List<Field> getAllNestSimpleFields(final Class<?> root) {
        List<Field> result = new ArrayList<>();
        for (Field field : FieldUtils.getAllFieldsList(root)) {
            if (MyClassUtils.isSimpleValueType(field.getType())) {
                result.add(field);
            } else {
                result.addAll(getAllNestSimpleFields(field.getType()));
            }
        }
        return result;
    }

    public static List<FieldTreeNode> getFieldPathWithAnnotation(Class<?> cls,
        Class<? extends Annotation> annotationCls) {

        if (!CLASS_FIELD_MARKED_PATH_CACHE.containsKey(cls)) {
            synchronized (MyFieldUtils.class) {
                if (!CLASS_FIELD_MARKED_PATH_CACHE.containsKey(cls)) {
                    List<FieldTreeNode> nodes = getFieldTree(cls);
                    markFieldPathWithAnnotation(nodes, annotationCls, new Stack<>());
                    removeNotMarkedFieldPath(nodes);
                    CLASS_FIELD_MARKED_PATH_CACHE.put(cls, nodes);
                }
            }
        }
        return CLASS_FIELD_MARKED_PATH_CACHE.get(cls);
    }

    /**
     * 获取一个类的 field 树
     * 
     * @param root
     * @return
     */
    public static List<FieldTreeNode> getFieldTree(final Class<?> root) {
        List<FieldTreeNode> result = new ArrayList<>();
        for (Field field : FieldUtils.getAllFieldsList(root)) {
            // 基本类型、基本类型数组
            if (MyClassUtils.isSimpleProperty(field.getType())) {
                result.add(new FieldTreeNode(field));
                continue;
            }

            // 对象类型数组
            if (field.getType().isArray()) {
                Class<?> arrayComponentClass = field.getType().getComponentType();
                result.add(new FieldTreeNode(field, getFieldTree(arrayComponentClass)));
                continue;
            }

            // 对象类型集合
            if (Collection.class.isAssignableFrom(field.getType())) {
                Class<?> collectionComponentClass = getCollectionComponentClass(field);
                result.add(new FieldTreeNode(field, getFieldTree(collectionComponentClass)));
                continue;
            }

            // 对象类型
            result.add(new FieldTreeNode(field, getFieldTree(field.getType())));
        }
        return result;
    }

    /**
     * 在一个类的 field 树上，对被标上指定注解的 field 进行标记。
     *
     * @param nodes
     * @param annotationCls
     * @param stack
     */
    private static void markFieldPathWithAnnotation(List<FieldTreeNode> nodes,
        Class<? extends Annotation> annotationCls, Stack<FieldTreeNode> stack) {

        for (FieldTreeNode node : nodes) {
            stack.push(node);
            // 一旦一个 field 被标记了，其所有的上级节点 field 都会被标记
            if (node.isAnnotationPresent(annotationCls)) {
                for (FieldTreeNode stackNode : stack) {
                    stackNode.setMarked(true);
                }
            }
            if (node.hasChildren()) {
                markFieldPathWithAnnotation(node.getChildren(), annotationCls, stack);
            }
            stack.pop();
        }
    }

    /**
     * 对一个类的 field 树进行裁剪，只保留被标记的路径
     * 
     * @param nodes
     */
    private static void removeNotMarkedFieldPath(List<FieldTreeNode> nodes) {
        Iterator<FieldTreeNode> iterator = nodes.iterator();
        while (iterator.hasNext()) {
            FieldTreeNode node = iterator.next();
            if (!node.isMarked()) {
                iterator.remove();
            }
            if (node.hasChildren()) {
                removeNotMarkedFieldPath(node.getChildren());
            }
        }
    }

    /**
     * 获取集合元素的类型
     * 
     * @param field
     * @return
     */
    private static Class<?> getCollectionComponentClass(Field field) {
        ParameterizedType type = (ParameterizedType)field.getGenericType();
        return (Class<?>)type.getActualTypeArguments()[0];
    }

    public static void main(String[] args) {
        List<FieldTreeNode> list = getFieldPathWithAnnotation(Person.class, Gzip.class);
        System.out.println(list);
    }
}
