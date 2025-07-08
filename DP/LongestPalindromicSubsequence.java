public class LongestPalindromicSubsequence {

    public static int lcs(String s1, String s2) {
        int n = s1.length();
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][n];
    }

    public static int longestPalindromicSubsequence(String s) {
        String rev = new StringBuilder(s).reverse().toString();
        return lcs(s, rev);
    }

    public static void main(String[] args) {
        String s = "agbcba";
        int result = longestPalindromicSubsequence(s);
        System.out.println("Length of Longest Palindromic Subsequence: " + result);
    }
}
