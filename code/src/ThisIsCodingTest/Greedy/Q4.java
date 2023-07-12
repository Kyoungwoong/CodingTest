package ThisIsCodingTest.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q4 {
    private static int N;
    private static int[] coins;
    private static int[] ans = new int[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        coins = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(coins); // 1 1 2 3 9

        for (int idx = 0; idx < N; idx++) {
            int coin = coins[idx];
            for (int i = 100; i > 0; i--) {
                if (i - coin == 0) {
                    ans[i]++;
                    break;
                } else if (ans[i - coin] != 0) {
                    ans[i]++;
                }
            }
        }

        for (int i = 1; i <= 100; i++) {
            if (ans[i] == 0) {
                System.out.println("i = " + i);
                System.exit(0);
            }
        }


    }
}
/*
5
3 2 1 1 9
 */
