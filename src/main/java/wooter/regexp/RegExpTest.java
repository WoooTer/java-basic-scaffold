package wooter.regexp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpTest {

    public static void main(String[] args) {
        // 查找的字符串
        String line = "大肠埃希菌[DSA][GPO]";
        //正则表达式
        String pattern = "(\\[)(.*?)(])"; //Java正则表达式以括号分组，第一个括号表示以"["开头，第三个括号表示以"]"结尾，中间括号为目标值，
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 创建 matcher 对象
        Matcher m = r.matcher(line);

        List<String> foundValueList = new ArrayList<>();
        while (m.find()) {
            /*
             自动遍历打印所有结果   group方法打印捕获的组内容，以正则的括号角标从1开始计算，我们这里要第2个括号里的
             值， 所以取 m.group(2)， m.group(0)取整个表达式的值，如果越界取m.group(4),则抛出异常
           */
            foundValueList.add(m.group(2));
            System.out.println("Found value: " + m.group(2));
        }

        String a = line.replaceAll("(\\[)(.*?)(])","");
        System.out.println(a);

        System.out.println(String.join(",", foundValueList));
    }
}
