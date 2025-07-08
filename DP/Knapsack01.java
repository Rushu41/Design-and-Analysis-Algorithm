public class Knapsack01 {
    public static void main(String[] args) {
        int[] weights = { 1, 3, 4, 5 };
        int[] values = { 1, 4, 5, 7 };
        int capacity = 7;
        int n = weights.length;

        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            int wt = weights[i - 1];
            int val = values[i - 1];
            for (int w = 0; w <= capacity; w++) {
                if (wt > w) {
                    dp[i][w] = dp[i - 1][w];
                    dp[i][w] = Math.max(
                            dp[i - 1][w],
                            val + dp[i - 1][w - wt]);
                }
            }
        }

        System.out.println("Maximum value in Knapsack = " + dp[n][capacity]);

        System.out.print("Items included (1-based index): ");
        int w = capacity;
        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                System.out.print(i + " ");
                w -= weights[i - 1];
            }
        }
    }
}
