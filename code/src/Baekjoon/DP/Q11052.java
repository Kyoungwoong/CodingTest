package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 카드의 갯수가 적더라도 비싸면 높은 등급의 확률 높다고 함
 * 카드 N개를 갖기 위해 지불해야하는 금액의 최댓값
 */
public class Q11052 {
    private static int N;
    private static int[] cards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

//        cards = new ArrayList<>();
        cards = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
//            cards.add(Integer.parseInt(st.nextToken()));
            cards[i + 1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        for (int won = 1; won <= N; won++) {
            for (int count = 1; count <= won; count++) {
                dp[won] = Math.max(dp[won], dp[won - count] + cards[count]);
            }
        }

//        for (int i = 0; i <= N; i++) {
//            System.out.print(dp[i] + " ");
//        }
//        System.out.println();
        System.out.println(dp[N]);
    }
}
