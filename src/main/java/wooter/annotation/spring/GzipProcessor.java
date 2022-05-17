package wooter.annotation.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import wooter.utils.BaseGzipUtils;

import java.lang.reflect.Field;

@Aspect
@Component
public class GzipProcessor {

    @Pointcut("@annotation(wooter.annotation.spring.Gzip)")
    public void handleCompressAnnotation() {}

    @Around("handleCompressAnnotation()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        if (args != null) {
            for (Object arg : args) {
                if (arg == null) {
                    continue;
                }
                if (arg.getClass().isAnnotationPresent(Gzip.class)) {
                    for (Field field : arg.getClass().getDeclaredFields()) {
                        if (field.isAnnotationPresent(Gzip.class)) {
                            field.setAccessible(true);
                            field.set(arg, BaseGzipUtils.compressGzip((String)field.get(arg)));
                        }
                    }
                }
            }
        }

        Object result = joinPoint.proceed();

        if (result != null) {
            if (result.getClass().isAnnotationPresent(Gzip.class)) {
                for (Field field : result.getClass().getDeclaredFields()) {
                    if (field.isAnnotationPresent(Gzip.class)) {
                        field.setAccessible(true);
                        field.set(result, BaseGzipUtils.uncompressGzip((String)field.get(result)));
                    }
                }
            }
        }

        return result;
    }
}
