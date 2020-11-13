package wooter.algorithm.leetcode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/reverse-integer/
 */
public class ReverseInteger {

    public int reverse_0(int x) {
        if (x == 0) {
            return 0;
        }

        int sign = 1;
        if (x < 0) {
            sign = -1;
        }

        Stack<String> stack = new Stack<>();
        String[] strArray = Integer.toString(Math.abs(x)).replaceFirst("-", "").split("");
        for (int i = 0; i < strArray.length; i++) {
            stack.push(strArray[i]);
        }

        StringBuilder strBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            strBuilder.append(stack.pop());
        }

        try {
            return Integer.parseInt(
                    strBuilder.toString().replaceAll("^(0+)", "")
            ) * sign;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int reverse_1(int x) {
        String xString = Integer.toString(x);
        String string = xString;

        int sign = 1;
        if (x < 0) {
            sign = -1;
            string = xString.substring(1);
        }

        try {
            return Integer.valueOf(new StringBuilder(string).reverse().toString()) * sign;
        } catch (Exception e) {
            return 0;
        }
    }


    public int reverse_2(int x) {
        int y = 0;
        while (x != 0) {
            // 如果 y = y * 10 + x % 10 溢出，
            // 则 y * 10 + x % 10 > Integer.MAX_VALUE 或 y * 10 + x % 10 < Integer.MIN_VALUE
            if (y > Integer.MAX_VALUE / 10
                    || y < Integer.MIN_VALUE / 10
                    || (y == Integer.MAX_VALUE / 10 && (x % 10 > Integer.MAX_VALUE % 10))
                    || (y == Integer.MIN_VALUE / 10 && (x % 10 < Integer.MIN_VALUE % 10))) {
                return 0;
            }
            y = y * 10 + x % 10;
            x = x / 10;
        }
        return y;
    }

    public static void main(String[] args) {
        int r = new ReverseInteger().reverse_2(Integer.MAX_VALUE);
        System.out.println(r);
    }
}
