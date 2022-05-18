package wooter.annotation.spring.service;

import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import wooter.annotation.spring.Gzip;
import wooter.utils.BaseGzipUtils;
import wooter.utils.MyClassUtils;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;

@Aspect
@Component
public class GzipNestProcessor {

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
    private void zipArgs(Object[] args) throws ReflectiveOperationException {
        if (args == null) {
            return;
        }
        for (Object arg : args) {
            if (arg == null) {
                continue;
            }

            List<Pair<Field, Object>> pairList = MyClassUtils.getAnnotatedValues(arg, new HashSet<>(), Gzip.class);
            for (Pair<Field, Object> pair : pairList) {
                Field field = pair.getLeft();
                Object argObj = pair.getRight();
                if (String.class.isAssignableFrom(field.getType())) {
                    field.setAccessible(true);
                    field.set(argObj, BaseGzipUtils.compressGzip((String)field.get(argObj)));
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
    private void unzipResult(Object result) throws ReflectiveOperationException {
        if (result == null) {
            return;
        }

        List<Pair<Field, Object>> pairList = MyClassUtils.getAnnotatedValues(result, new HashSet<>(), Gzip.class);
        for (Pair<Field, Object> pair : pairList) {
            Field field = pair.getLeft();
            Object argObj = pair.getRight();
            if (String.class.isAssignableFrom(field.getType())) {
                field.setAccessible(true);
                field.set(argObj, BaseGzipUtils.uncompressGzip((String)field.get(argObj)));
            }
        }
    }
}
