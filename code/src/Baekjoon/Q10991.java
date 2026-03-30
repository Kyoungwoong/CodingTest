package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q10991 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        dfs(1, N);

        System.out.println(sb.toString());
    }

    private static void dfs(int pos, int limit) {
        for (int space = 0; space < limit - 1; space++) {
            sb.append(" ");
        }

        for (int idx = 0; idx < pos; idx++) {
            sb.append("*").append(" ");
        }
        sb.append("\n");

        if (pos == N) {
            return;
        }

        dfs(pos + 1, limit - 1);
    }
}
