package ThisIsCodingTest.DP;

public class Fibo {
    public static long [] dp = new long[100];
    public static long fibo(int n){
        System.out.print(n+" ");
        if(n == 1 || n == 2){
            return 1;
        }
        if(dp[n] != 0){
            return dp[n];
        }
        dp[n] = fibo(n-1) + fibo(n-2);
        return dp[n];
    }
    public static void main(String[] args) {
        System.out.println(fibo(50));
//        for(int i = 0; i < 100; i++){
//            System.out.println(dp[i]);
//        }
    }
}
