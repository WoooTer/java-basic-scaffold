package wooter.algorithm.iamshuaidi;

public class DynamicProgramming {

    public static void main(String[] args) {
        test_twoDimensionStep();
        test_minStepWeight();
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

    static void test_twoDimensionStep(){
        System.out.println("递归: " + twoDimensionStepByRecursion(3, 7));
        System.out.println("动归: " + twoDimensionStepByDP(3, 7));
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

    static int minStepWeightByDP(int[][] arr) {
        int x = arr.length - 1;
        int y = arr[0].length - 1;
        int[][] dp = new int[x][y];

        dp[0][0] = arr[0][0];
        for (int i = 1; i < x; i ++){
            dp[i][0] = dp[i - 1][0] + arr[i][0];
        }
        for (int i = 1; i < x; i ++){
            dp[0][i] = dp[0][i - 1] + arr[0][i];
        }

        for (int i = 1; i < x; i ++){
            for (int j = 1; j < y; j ++){
                dp[i][j] = Math.min(
                        dp[i - 1][j],
                        dp[i][j - 1]
                ) + arr[i][j];
            }
        }
        return dp[x - 1][y - 1];
    }

    static void test_minStepWeight(){
        int arr[][] = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println("递归: " + minStepWeightByRecursion(arr));
        System.out.println("动归: " + minStepWeightByDP(arr));
    }

}
