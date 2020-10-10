package wooter.algorithm.iamshuaidi;

public class DynamicProgramming {

    public static void main(String[] args) {
//        System.out.println(twoDimensionStepByDP(3, 7));

        int arr[][] = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minStepWeightByRecursion(arr));
    }

    /**
     * 小青蛙跳台阶：
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法
     */
    static int stepProblem(int n) {
        if (n <= 2) {
            return n;
        }
        // 先创建一个数组来保存历史数据
        int[] dp = new int[n + 1];
        // 给出初始值
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        // 通过关系式来计算出 dp[n]
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * [不同路径问题](https://leetcode-cn.com/problems/unique-paths/)
     */
    static int twoDimensionStepByRecursion(int x, int y) {
        if (x <= 0 || y <= 0) {
            throw new RuntimeException("入参不可比1小");
        }
        if (x == 1 && y == 1) {
            return 0;
        }
        if (x == 1 || y == 1) {
            return 1;
        }
        return twoDimensionStepByRecursion(x - 1, y) + twoDimensionStepByRecursion(x, y - 1);
    }

    static int twoDimensionStepByDP(int x, int y) {
        if (x <= 0 || y <= 0) {
            throw new RuntimeException("入参不可比1小");
        }

        int[][] dp = new int[x][y];

        dp[0][0] = 0;
        for (int i = 1; i < x; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j < y; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < x; i++) {
            for (int j = 1; j < y; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[x - 1][y - 1];
    }

    /**
     * [最小路径和](https://leetcode-cn.com/problems/minimum-path-sum/)
     */
    static int minStepWeightByRecursion(int[][] arr) {
        int x = arr.length - 1;
        int y = arr[0].length - 1;
        return _minStepWeightByRecursion(arr, x, y);
    }

    static int _minStepWeightByRecursion(int[][] arr, int i, int j) {
        if (i == 0 && j == 0) {
            return arr[0][0];
        }
        if (i == 0 && j > 0) {
            int _v = 0;
            for (int k = 0; k <= j; k++) {
                _v += arr[0][k];
            }
            return _v;
        }
        if (j == 0 && i > 0) {
            int _v = 0;
            for (int k = 0; k <= i; k++) {
                _v += arr[k][0];
            }
            return _v;
        }

        return Math.min(
                _minStepWeightByRecursion(arr, i - 1, j),
                _minStepWeightByRecursion(arr, i, j - 1)
        ) + arr[i][j];
    }


}
