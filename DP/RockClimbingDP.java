
public class RockClimbingDP {
    static int INF = Integer.MAX_VALUE;

    public static int minDangerPath(int[][] C) {
        int n = C.length;
        int m = C[0].length;
        int[][] A = new int[n + 1][m + 2];

        for (int j = 0; j <= m + 1; j++) {
            A[0][j] = 0;
        }

        for (int i = 1; i <= n; i++) {
            A[i][0] = A[i][m + 1] = INF;
        }

        for (int i = 1; i <= n; i++) {
            System.out.println("Filling Row " + i + ":");
            for (int j = 1; j <= m; j++) {
                int danger = C[i - 1][j - 1];
                A[i][j] = danger + Math.min(
                        A[i - 1][j - 1],
                        Math.min(A[i - 1][j], A[i - 1][j + 1]));
                System.out.printf("A[%d][%d] = C[%d][%d](%d) + min(%d, %d, %d) = %d\n",
                        i, j, i - 1, j - 1, danger,
                        A[i - 1][j - 1], A[i - 1][j], A[i - 1][j + 1],
                        A[i][j]);
            }
            System.out.println();
        }

        System.out.println("\nFinal DP Table (A):");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                System.out.printf("%4d", A[i][j]);
            }
            System.out.println();
        }

        int minDanger = INF;
        for (int j = 1; j <= m; j++) {
            minDanger = Math.min(minDanger, A[n][j]);
        }

        return minDanger;
    }

    public static void main(String[] args) {
        int[][] C = {
                { 3, 4, 1 },
                { 6, 1, 8 },
                { 5, 9, 3 }
        };

        int result = minDangerPath(C);
        System.out.println("\n🔺 Minimum Danger Rating to Reach the Top: " + result);
    }
}
