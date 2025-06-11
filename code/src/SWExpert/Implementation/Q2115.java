package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q2115 {
    private static int tc, N, M, C;
    private static int[][] honeys;
    private static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            honeys = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    honeys[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int ans = 0;

            for (int r1 = 0; r1 < N; r1++) {
                for (int c1 = 0; c1 <= N - M; c1++) {
                    int[] first = new int[M];
                    System.arraycopy(honeys[r1], c1, first, 0, M);
                    int profitA = getMaxProfit(first);

                    for (int r2 = r1; r2 < N; r2++) {
                        for (int c2 = 0; c2 <= N - M; c2++) {
                            // 겹치는 경우 제외
                            if (r1 == r2 && c2 < c1 + M && c2 + M > c1) continue;

                            int[] second = new int[M];
                            System.arraycopy(honeys[r2], c2, second, 0, M);
                            int profitB = getMaxProfit(second);

                            ans = Math.max(ans, profitA + profitB);
                        }
                    }
                }
            }

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }

        System.out.print(sb);
    }

    // 부분집합 탐색으로 수익의 최대값 계산
    private static int getMaxProfit(int[] arr) {
        max = 0;
        subset(0, 0, 0, arr);
        return max;
    }

    private static void subset(int idx, int sum, int profit, int[] arr) {
        if (sum > C) return;

        if (idx == M) {
            max = Math.max(max, profit);
            return;
        }

        // 선택
        subset(idx + 1, sum + arr[idx], profit + arr[idx] * arr[idx], arr);
        // 선택 안함
        subset(idx + 1, sum, profit, arr);
    }
}

/*
10
4 2 13
6 1 9 7
9 8 5 8
3 4 5 3
8 2 6 7
3 3 10
7 2 9
6 6 6
5 5 7
4 1 10
8 1 8 2
4 6 1 6
4 9 6 3
7 4 1 3
4 3 12
8 8 6 5
5 2 7 4
8 5 1 7
7 8 9 4
5 2 11
7 5 9 9 6
7 3 7 9 3
1 7 1 4 5
1 7 9 2 6
6 6 8 3 8
6 3 20
8 5 2 4 3 1
4 3 6 1 1 8
4 4 1 2 3 1
1 7 4 9 6 1
6 5 1 2 8 4
3 1 4 5 1 3
7 2 11
2 8 2 5 2 8 6
2 3 7 4 6 4 8
3 7 8 3 9 4 4
8 8 5 9 3 6 9
9 7 6 2 4 1 3
2 9 2 8 9 7 3
2 1 7 2 7 8 3
8 3 12
9 1 6 7 5 4 6 7
9 5 1 8 8 3 5 8
5 2 6 8 6 9 2 1
9 2 1 8 7 5 2 3
6 5 5 1 4 5 7 2
1 7 1 8 1 9 5 5
6 2 2 9 2 5 1 4
7 1 1 2 5 9 5 7
9 4 20
5 2 4 8 3 7 6 2 1
7 9 8 5 8 2 6 3 6
1 9 4 6 7 5 3 1 1
4 4 7 6 2 2 8 1 7
9 6 8 5 7 3 7 9 5
7 3 1 4 1 1 8 5 3
4 6 8 9 4 5 3 8 8
1 3 4 2 4 1 1 3 6
5 9 2 3 5 2 4 8 5
10 5 30
7 4 7 5 9 3 6 4 6 7
8 9 8 4 5 7 8 9 2 9
6 5 3 4 6 4 7 6 3 2
4 7 4 3 4 7 3 3 4 3
3 5 6 4 8 8 2 1 8 6
3 7 9 7 1 7 6 2 8 9
3 6 1 6 8 9 7 7 5 1
4 3 5 6 2 1 2 6 3 6
3 4 9 2 1 5 9 9 6 3
9 9 7 3 7 5 5 5 8 4

 */