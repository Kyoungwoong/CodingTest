package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2001 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();

    private static int testCase, N, M, ans;
    private static int[][] flies;

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine());

        for (int test = 1; test <= testCase; test++) {
            input();

            solve();

            sb.append(String.format("#%d %d\n", test, ans));
        }
        System.out.println(sb.toString());
    }

    private static void solve() {
        for (int row = 0; row <= N - M; row++) {
            for (int col = 0; col <= N - M; col++) {
                int sum = 0;
                for (int x = row; x < row + M; x++) {
                    for (int y = col; y < col + M; y++) {
                        sum += flies[x][y];
                    }
                }
                ans = Math.max(ans, sum);
            }
        }
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        flies = new int[N ][N];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                flies[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        ans = Integer.MIN_VALUE;
    }
}
