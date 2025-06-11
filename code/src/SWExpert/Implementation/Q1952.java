package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1952 {
    private static int tc, totalFee;
    private static int[] fees;
    private static int[] months;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            fees = new int[4]; // 0: 하루, 1: 한달, 2: 3개월, 3: 1년 이용료
            for (int i = 0; i < 4; i++) {
                fees[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            months = new int[13];
            for (int i = 1; i <= 12; i++) {
                months[i] = Integer.parseInt(st.nextToken());
            }

            totalFee = fees[3];
            for (int i = 1; i <= 12; i++) {
                if (months[i] > 0) {
                    dfs(i, 0);
                    break;
                }
            }
            sb.append("#").append(t).append(" ").append(totalFee).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int month, int sum) {
        // 재귀 종료 조건
        if (month > 12) {
            totalFee = Math.min(totalFee, sum);
            return;
        }

        // 하루 이용
        dfs(month + 1, sum + (months[month] * fees[0]));

        // 한달 이용
        dfs(month + 1, sum + fees[1]);

        // 세달 이용
        dfs(month + 3, sum + fees[2]);
    }
}
/*
10
10 40 100 300
0 0 2 9 1 5 0 0 0 0 0 0
10 100 50 300
0 0 0 0 0 0 0 0 6 2 7 8
10 70 180 400
6 9 7 7 7 5 5 0 0 0 0 0
10 70 200 550
0 0 0 0 8 9 6 9 6 9 8 6
10 80 200 550
0 8 9 15 1 13 2 4 9 0 0 0
10 130 360 1200
0 0 0 15 14 11 15 13 12 15 10 15
10 180 520 1900
0 18 16 16 19 19 18 18 15 16 17 16
10 100 200 1060
12 9 11 13 11 8 6 12 8 7 15 6
10 170 500 1980
19 18 18 17 15 19 19 16 19 15 17 18
10 200 580 2320
12 28 24 24 29 25 23 26 26 28 27 22
 */
