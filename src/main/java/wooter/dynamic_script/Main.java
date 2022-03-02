package wooter.dynamic_script;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        t3();
    }

    public static void t1() {
        String expression = "($100 + (b-c) > 100) && $100 <= 100";
        // 编译表达式
        Expression compiledExp = AviatorEvaluator.compile(expression);

        Map<String, Object> env = new HashMap<>();
        env.put("$100", 100.3);
        env.put("b", 45);
        env.put("c", -199.100);

        // 执行表达式
        Boolean result = (Boolean) compiledExp.execute(env);
        Boolean result2 = (Boolean) compiledExp.execute(env);
        System.out.println(result);
    }

    public static void t2() {
        String expression = "if (a > 100) { return 100.00; } else { return a; }";
        // 编译表达式
        Expression compiledExp = AviatorEvaluator.compile(expression);

        Map<String, Object> env = new HashMap<>();
        env.put("a", 99.9);

        // 执行表达式
        Double result = (Double) compiledExp.execute(env);
        System.out.println(result);
    }

    public static void t3() {
        String expression = "10 / a";
        // 编译表达式
        Expression compiledExp = AviatorEvaluator.compile(expression);

        Map<String, Object> env = new HashMap<>();
        env.put("a", 0);

        // 执行表达式
        Long result = (Long) compiledExp.execute(env);
        System.out.println(result);
    }
}
