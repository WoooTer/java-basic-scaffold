package wooter.algorithm.iamshuaidi;

import java.util.Arrays;

/**
 * [彻底理解0-1背包问题](https://blog.csdn.net/chanmufeng/article/details/82955730)
 * [一次搞定三种背包问题](https://www.cnblogs.com/mfrank/p/10849505.html)
 * [什么是P问题、NP问题和NPC问题](http://www.matrix67.com/blog/archives/105)
 */
public class KnapsackProblem {

    public static void main(String[] args) {
        test_knapsack();
    }

    /**
     * 解决背包问题的递归函数
     *
     * @param ws 物品的重量数组
     * @param vs 物品的价值数组
     * @param i  当前待选择的物品索引
     * @param c  当前背包有效容量
     * @return 最大价值
     */

    static int knapsackByRecursion(int[] ws, int[] vs, int i, int c) {
        if (i < 0 || c <= 0) {
            // 初始条件
            return 0;
        } else if (ws[i] > c) {
            // 装不下该物品
            return knapsackByRecursion(ws, vs, i - 1, c);
        } else {
            // 可以装下
            int tmp1 = knapsackByRecursion(ws, vs, i - 1, c);
            int tmp2 = knapsackByRecursion(ws, vs, i - 1, c - ws[i]) + vs[i];
            return Math.max(tmp1, tmp2);
        }
    }

    static int knapsackByDP(int[] ws, int[] vs, int i, int c) {
        int[][] dp = new int[ws.length][c+1];

        // 初始化
        for (int m = 0; m <= c; m++) {
            dp[0][m] = ws[0] <= m ? vs[0] : 0;
        }
        // 开始填表
        for (int m = 1; m <= i; m++) {
            for (int n = 0; n <= c; n++) {
                if (n < ws[m]) {
                    // 装不进去
                    dp[m][n] = dp[m - 1][n];
                } else {
                    // 容量足够
                    dp[m][n] = Math.max(dp[m - 1][n], dp[m - 1][n - ws[m]] + vs[m]);
                }
            }
        }
        _printDpArray(dp);
        return dp[i][c];
    }

    static void _printDpArray(int[][] dp){
        //System.out.println(Arrays.deepToString(dp));
        Arrays.asList(dp).forEach(e -> System.out.println(Arrays.toString(e)));
    }

    static void test_knapsack() {
        int[] vs = {2, 4, 3, 7};
        int[] ws = {2, 3, 5, 5};
        int c = 10;
        int i = vs.length - 1;
        System.out.println(knapsackByRecursion(ws, vs, i, c));
        System.out.println(knapsackByDP(ws, vs, i, c));
    }

}
