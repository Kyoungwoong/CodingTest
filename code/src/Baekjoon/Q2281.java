package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2281 {
    // https://www.acmicpc.net/problem/2281
    static int N, M;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        dp = new int[N][M + 1];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 0 ; i < N ; i ++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(solution(0, 0));
    }

    public static int solution(int idx, int usedCnt) {
        if(idx == N - 1 || usedCnt > M) return 0;
        if(dp[idx][usedCnt] != -1) return dp[idx][usedCnt];

        int ret = Integer.MAX_VALUE;
        if (M >= arr[idx] + usedCnt) {
            ret = Math.min(ret, solution(idx + 1, usedCnt + arr[idx] + 1));
        }

        int spare = M - usedCnt + 1;
        ret = Math.min(ret, solution(idx + 1, arr[idx] + 1) + (int) Math.pow(spare, 2));

        return dp[idx][usedCnt] = ret;
    }
}

/*
11 20
7
4
2
3
2
5
1
12
7
5
6
// ans: 61
 */