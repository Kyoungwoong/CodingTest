package ThisIsCodingTest.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Gaemi {
    public static int N;
    public static int[] arr;
    public static int[] dp;

    public static void main(String[] args) throws IOException {
//        prev();
        oct15();
    }

    private static void oct15() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // i번째 도착했을 때 얻을 수 있는 최대값.
        dp[0] = arr[0];
        dp[1] = arr[1];
        dp[2] = arr[0] + arr[2];
        for (int i = 3; i < N; i++) {
            dp[i] = Math.max(dp[i-2] + arr[i], dp[i-3] + arr[i]);
        }

        System.out.println("dp[N-1] = " + dp[N - 1]);
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp[0] = arr[0];
        dp[1] = arr[1];
        dp[2] = dp[0]+arr[2];

        int ans = -1;

        for(int i = 3; i < N; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2]+arr[i]);
        }

        System.out.println("ans = " + dp[N-1]);
    }
}
//4
//1 3 1 5
//// 8
