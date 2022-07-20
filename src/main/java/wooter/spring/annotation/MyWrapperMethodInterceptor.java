package wooter.spring.annotation;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyWrapperMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object result = null;
        try {
            System.out.println("invocation start");
            result = invocation.proceed();
            System.out.println("invocation end");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }
}
