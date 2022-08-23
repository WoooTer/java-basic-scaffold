package wooter.spring.bean.field.proxy.annotation;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyBeanFieldProxyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object result = null;
        try {
            System.out.println("proxy start");
            result = invocation.proceed();
            System.out.println("proxy end");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }
}
