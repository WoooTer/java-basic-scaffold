package wooter.design_pattern.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * https://www.baeldung.com/java-dynamic-proxies
 */
public class DynamicInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.printf("Invoked method: %s \n", method.getName());
        return 42;
    }

    public static void main(String[] args) {
        Map proxyInstance = (Map) Proxy.newProxyInstance(
                Object.class.getClassLoader(),
                new Class[]{Map.class},
                new DynamicInvocationHandler());
        proxyInstance.put("hello", "world");
        System.out.println(proxyInstance.get("hello"));
    }
}
