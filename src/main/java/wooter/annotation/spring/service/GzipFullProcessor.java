package wooter.annotation.spring.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import wooter.annotation.spring.Gzip;
import wooter.annotation.spring.field.FieldTreeNode;
import wooter.annotation.spring.field.MyFieldUtils;
import wooter.utils.BaseGzipUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * 全面支持类及其父类的自己 field 以及嵌套 field, 并且集合 field
 * 
 * 同时预处理过的类进行缓存，提升性能
 */
@Aspect
@Component
public class GzipFullProcessor {

    @Pointcut("@annotation(wooter.annotation.spring.Gzip)")
    public void handleCompressAnnotation() {}

    @Around("handleCompressAnnotation()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        zipArgs(joinPoint.getArgs());
        Object result = joinPoint.proceed();
        unzipResult(result);
        return result;
    }

    /**
     * 压缩入参
     *
     * @param args
     * @throws IllegalAccessException
     */
    private void zipArgs(Object[] args) throws IllegalAccessException {
        if (args == null) {
            return;
        }
        for (Object arg : args) {
            if (arg == null) {
                continue;
            }
            List<FieldTreeNode> nodes = MyFieldUtils.getFieldPathWithAnnotation(arg.getClass(), Gzip.class);
            processArgs(nodes, arg, BaseGzipUtils::compressGzip);
        }
    }

    /**
     * 解压出参
     *
     * @param result
     * @throws IllegalAccessException
     */
    private void unzipResult(Object result) throws IllegalAccessException {
        if (result == null) {
            return;
        }
        List<FieldTreeNode> nodes = MyFieldUtils.getFieldPathWithAnnotation(result.getClass(), Gzip.class);
        processArgs(nodes, result, BaseGzipUtils::uncompressGzip);
    }

    private void processArgs(List<FieldTreeNode> nodes, Object root, Function<String, String> gzipFunc)
        throws IllegalAccessException {
        for (FieldTreeNode node : nodes) {
            Field field = node.getNode();
            field.setAccessible(true);
            if (field.get(root) == null) {
                continue;
            }

            /**
             * 没有子节点的 field, 一定是基本类型, 包括基本类型数组
             */
            if (!node.hasChildren()) {
                // 字符串数组
                if (node.isArray() && String.class.isAssignableFrom(field.getType().getComponentType())) {
                    String[] strArray = (String[])field.get(root);
                    for (int i = 0; i < strArray.length; i++) {
                        strArray[i] = gzipFunc.apply(strArray[i]);
                    }
                } else if (String.class.isAssignableFrom(field.getType())) {
                    // 字符串
                    field.set(root, gzipFunc.apply((String)field.get(root)));
                } else {
                    // 对于非字符串的基本类型，则不作任何处理
                }
            }

            /**
             * 有子节点的 field, 一定是对象类型, 包括对象类型数组、对象类型集合
             */
            if (node.hasChildren()){
                // 对象类型数组
                if (node.isArray()) {
                    for (Object obj : (Object[])field.get(root)) {
                        processArgs(node.getChildren(), obj, gzipFunc);
                    }
                } else if (node.isCollection()) {
                    // 对象类型集合
                    for (Object obj : (Collection)field.get(root)) {
                        processArgs(node.getChildren(), obj, gzipFunc);
                    }
                } else {
                    // 对象类型
                    processArgs(node.getChildren(), field.get(root), gzipFunc);
                }
            }
        }
    }
}
