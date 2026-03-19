package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1182 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, S, ans = 0;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        input();
        for (int count = 1; count <= N; count++) {
            solve(0, 0, count, 0);
        }
        System.out.println(ans);
    }

    private static void solve(int pos, int selectCount, int limit, int sum) {
        if (selectCount == limit) {
            if (sum == S) {
                ans++;
            }
            return;
        }

        if (N - pos < limit - selectCount) return;

        for (int i = pos; i < N; i++) {
            solve(i + 1, selectCount + 1, limit, sum + arr[i]);
        }
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        visited = new boolean[N];
    }
}
