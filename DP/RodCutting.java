public class RodCutting {
    public static int rodCutting(int[] price, int n) {
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], price[i - 1] + dp[i][j - i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][n];
    }

    public static void main(String[] args) {
        int[] price = { 2, 5, 7, 8 };
        int n = price.length;
        System.out.println("Maximum Profit: " + rodCutting(price, n));
    }
}
