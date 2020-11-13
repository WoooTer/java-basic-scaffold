package wooter.algorithm.leetcode.StringToIntegerAtoi;

public class StringToIntegerAtoi_1 {

    public int myAtoi(String s) {
        int i = 0;
        int y = 0;
        int flag = 1;

        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }
        if (i < s.length()) {
            if (s.charAt(i) == '-') {
                flag = -1;
            }
            if (s.charAt(i) == '-' || s.charAt(i) == '+') {
                i++;
            }
        }
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            int r = s.charAt(i) - '0';
            if (y > Integer.MAX_VALUE / 10
                    || (y == Integer.MAX_VALUE / 10 && r > Integer.MAX_VALUE % 10)) {
                return flag == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            y = y * 10 + r;
            i++;
        }
        return y * flag;
    }

    public static void main(String[] args) {
        int r = new StringToIntegerAtoi_1().myAtoi("-+12");
        System.out.println(r);
    }
}
