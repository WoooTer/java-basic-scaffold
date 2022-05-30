package wooter.annotation.spring.service;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import wooter.annotation.spring.Gzip;
import wooter.utils.BaseGzipUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 只支持类及其父类的自己 field, 且不支持集合 field
 */
//@Aspect
//@Component
public class GzipProcessor {

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
            List<Field> fields = FieldUtils.getFieldsListWithAnnotation(arg.getClass(), Gzip.class);
            for (Field field : fields) {
                if (String.class.isAssignableFrom(field.getType())) {
                    field.setAccessible(true);
                    field.set(arg, BaseGzipUtils.compressGzip((String)field.get(arg)));
                }
            }
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
        List<Field> fields = FieldUtils.getFieldsListWithAnnotation(result.getClass(), Gzip.class);
        for (Field field : fields) {
            if (String.class.isAssignableFrom(field.getType())) {
                field.setAccessible(true);
                field.set(result, BaseGzipUtils.uncompressGzip((String)field.get(result)));
            }
        }
    }
}
