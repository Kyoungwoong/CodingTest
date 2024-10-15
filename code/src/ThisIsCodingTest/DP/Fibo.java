package ThisIsCodingTest.DP;

public class Fibo {
    public static long [] dp = new long[100];
    public static long prevFibo(int n){
        System.out.print(n+" ");
        if(n == 1 || n == 2){
            return 1;
        }
        if(dp[n] != 0){
            return dp[n];
        }
        dp[n] = prevFibo(n-1) + prevFibo(n-2);
        return dp[n];
    }
    public static void main(String[] args) {
        System.out.println("\n" + prevFibo(50));
//        for(int i = 0; i < 100; i++){
//            System.out.println(dp[i]);
//        }

        System.out.println("oct15(50) = " + oct15_iterative(50));
        System.out.println("oct15_recursive(50) = " + oct15_recursive(50));
    }

    private static long oct15_iterative(int n) {
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    private static long oct15_recursive(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        if (dp[n] != 0) {
            return dp[n];
        }
        dp[n] = oct15_recursive(n-2) + oct15_recursive(n-1);

        return dp[n];
    }
}
