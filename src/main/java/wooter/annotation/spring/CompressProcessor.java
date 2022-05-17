package wooter.annotation.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CompressProcessor {

    @Pointcut("@annotation(wooter.annotation.spring.Compress)")
    public void handleCompressAnnotation() {}

    @Around("handleCompressAnnotation()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("start");
        Object result = joinPoint.proceed();
        System.out.println("end");
        return result;
    }
}
