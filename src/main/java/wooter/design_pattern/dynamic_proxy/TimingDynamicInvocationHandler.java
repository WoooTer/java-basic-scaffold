package wooter.design_pattern.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.baeldung.com/java-dynamic-proxies
 */
public class TimingDynamicInvocationHandler implements InvocationHandler {

    private final Map<String, Method> methods = new HashMap<>();

    private Object target;

    public TimingDynamicInvocationHandler(Object target) {
        this.target = target;

        for (Method method : target.getClass().getDeclaredMethods()) {
            this.methods.put(method.getName(), method);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        long start = System.nanoTime();
        Object result = methods.get(method.getName()).invoke(target, args);
        long elapsed = System.nanoTime() - start;

        System.out.printf("Executing %s finished in %s ns \n", method.getName(), elapsed);

        return result;
    }

    public static void main(String[] args) {
        Map mapProxyInstance = (Map) Proxy.newProxyInstance(
                Object.class.getClassLoader(),
                new Class[]{Map.class},
                new TimingDynamicInvocationHandler(new HashMap<>()));

        mapProxyInstance.put("hello", "world");

        CharSequence csProxyInstance = (CharSequence) Proxy.newProxyInstance(
                Object.class.getClassLoader(),
                new Class[]{CharSequence.class},
                new TimingDynamicInvocationHandler("Hello World"));

        csProxyInstance.length();
    }
}
