package ThisIsCodingTest.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Counseling{
    int during, pay;

    public Counseling(int during, int pay) {
        this.during = during;
        this.pay = pay;
    }
}

public class Resignation {
    private static int N;
    private static int[] dp;
    private static Counseling[] schedule;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];

        schedule = new Counseling[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            schedule[i] = new Counseling(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // i번째 날부터 N번째 날까지 가질 수 있는 이익.
        int maxPay = 0;
        for (int i = N - 1; i >= 0; i--) {
            int endDay = schedule[i].during + i;

            if (endDay <= N) {
                dp[i] = Math.max(schedule[i].pay + dp[endDay], maxPay);
                maxPay = dp[i];
            }else{
                dp[i] = maxPay;
            }
        }
        System.out.println(maxPay);
    }
}
/*
7
3 10
5 20
1 10
1 20
2 15
4 40
2 200
--- 45

10
1 1
1 2
1 3
1 4
1 5
1 6
1 7
1 8
1 9
1 10
--- 55

10
5 10
5 9
5 8
5 7
5 6
5 10
5 9
5 8
5 7
5 6
--- 20

10
5 50
4 40
3 30
2 20
1 10
1 10
2 20
3 30
4 40
5 50
--- 90
 */
