package wooter.algorithm.wooter.bitOperation;

public class EfficientMath {

    /**
     * https://www.geeksforgeeks.org/add-two-numbers-without-using-arithmetic-operators/
     */
    public static long add(long x, long y) {
        // Iterate till there is no carry
        while (y != 0) {
            // carry now contains common
            // set bits of x and y
            long carry = x & y;

            // Sum of bits of x and
            // y where at least one
            // of the bits is not set
            x = x ^ y;

            // Carry is shifted by
            // one so that adding it
            // to x gives the required sum
            y = carry << 1;
        }
        return x;
    }

    public static long minus(long a, long b) {
        long B = ~(b - 1);
        return add(a, B);
    }

    /**
     * https://www.geeksforgeeks.org/multiply-two-numbers-without-using-multiply-division-bitwise-operators-and-no-loops/
     */
    public static long multiply(long x, long y) {
        int i = 0;
        int res = 0;
        while (y != 0) {//乘数为0则结束
            //处理乘数当前位
            if ((y & 1) == 1) {
                res += (x << i);
                y = y >> 1;
                ++i;//i记录当前位是第几位
            } else {
                y = y >> 1;
                ++i;
            }
        }
        return res;
    }

    /**
     * https://www.geeksforgeeks.org/divide-two-integers-without-using-multiplication-division-mod-operator/
     */
    public static long divide(long dividend, long divisor) {
        long sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;

        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        long quotient = 0, temp = 0;
        for (int i = 31; i >= 0; --i) {
            if (temp + (divisor << i) <= dividend) {
                temp += divisor << i;
                quotient |= 1L << i;
            }
        }
        return multiply(sign, quotient);
    }

    public static void main(String[] args) {
        long a = 432;
        long b = 38;
        long result = EfficientMath.minus(42, 8);
        System.out.println(result);
    }
}
