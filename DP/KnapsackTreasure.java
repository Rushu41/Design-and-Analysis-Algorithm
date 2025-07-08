import java.util.*;

public class KnapsackTreasure {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int W = sc.nextInt();

        int[] weights = new int[n + 1];
        int[] values = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            weights[i] = sc.nextInt();
            values[i] = sc.nextInt();
        }

        long[][] dp = new long[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                dp[i][w] = dp[i - 1][w];
                if (w >= weights[i]) {
                    dp[i][w] = Math.max(dp[i][w], values[i] + dp[i - 1][w - weights[i]]);
                }
            }
        }

        System.out.println(dp[n][W]);

        ArrayList<Integer> selected = new ArrayList<>();
        int w = W;
        for (int i = n; i >= 1; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                selected.add(i);
                w -= weights[i];
            }
        }

        Collections.sort(selected);
        for (int idx : selected) {
            System.out.print(idx + " ");
        }
        System.out.println();
    }
}
