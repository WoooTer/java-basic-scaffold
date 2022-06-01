package wooter.design_pattern.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyFactory<T> implements MethodInterceptor {

    private T target;

    public ProxyFactory(T target) {
        this.target = target;
    }

    /**
     * 创建代理对象
     * 
     * @return
     */
    public T getProxyInstance() {
        // 1.cglib工具类
        Enhancer en = new Enhancer();
        // 2.设置父类
        en.setSuperclass(this.target.getClass());
        // 3.设置回调函数
        en.setCallback(this);
        return (T)en.create();
    }

    /**
     * 拦截方法
     * 
     * @param obj
     * @param method
     * @param args
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始事务...");
        // 执行目标对象的方法
        Object result = method.invoke(target, args);
        System.out.println("提交事务...");
        return result;
    }
}