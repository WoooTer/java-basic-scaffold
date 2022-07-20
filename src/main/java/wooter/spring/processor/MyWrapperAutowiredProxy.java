package wooter.spring.processor;

import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.target.SingletonTargetSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import wooter.spring.annotation.MyWrapperMethodInterceptor;
import wooter.spring.annotation.MyWarpper;

@Service
public class MyWrapperAutowiredProxy implements MergedBeanDefinitionPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        ReflectionUtils.doWithFields(bean.getClass(), field -> {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }

            MyWarpper myWarpper = field.getAnnotation(MyWarpper.class);
            if (myWarpper == null) {
                return;
            }

            Object target = AopProxyUtils.getSingletonTarget(bean);
            if (target == null) {
                target = bean;
            }

            Object fieldObject = field.get(target);

            final ProxyFactory proxyFactory = new ProxyFactory();
            proxyFactory.addAdvisors(new DefaultPointcutAdvisor(new MyWrapperMethodInterceptor()));
            proxyFactory.setTargetSource(new SingletonTargetSource(fieldObject));

            final Object proxy = proxyFactory.getProxy(MyWarpper.class.getClassLoader());
            field.set(bean, proxy);
        });
        return bean;
    }

    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition rootBeanDefinition, Class<?> aClass, String s) {

    }
}
