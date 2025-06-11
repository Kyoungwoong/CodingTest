package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q4131 {
    private static int tc, N, X, ans = 0;
    private static int[][] stairs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            stairs = new int[N][N];
            ans = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    stairs[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 구하기
            // 가로
            for (int i = 0; i < N; i++) {
                if (canInstalled(stairs[i])) {
                    ans++;
                }
            }

            // 세로
            // 세로 방향
            for (int j = 0; j < N; j++) {
                int[] col = new int[N];
                for (int i = 0; i < N; i++) {
                    col[i] = stairs[i][j];
                }
                if (canInstalled(col)) ans++;
            }

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static boolean canInstalled(int[] line) {
        boolean[] alreadyInstalled = new boolean[N];

        for (int i = 0; i < N - 1; i++) {
            int diff = line[i + 1] - line[i];

            if (diff == 0) continue;
            if (Math.abs(diff) > 1) return false;

            if (diff == 1) {
                // 올라가는 계단
                for (int j = i; j > i - X; j--) {
                    if (j < 0 || alreadyInstalled[j] || line[i] != line[j]) {
                        return false;
                    }
                    alreadyInstalled[j] = true;
                }
            } else if (diff == -1) {
                // 내려가는 계단
                for (int j = i+1; j <= i + X; j++) {
                    if (j >= N || alreadyInstalled[j] || line[i+1] != line[j]) {
                        return false;
                    }
                    alreadyInstalled[j] = true;
                }
            }
        }

        return true;
    }
}

/*
2
6 2
3 3 3 2 1 1
3 3 3 2 2 1
3 3 3 3 3 2
2 2 3 2 2 2
2 2 3 2 2 2
2 2 2 2 2 2
6 4
3 2 2 2 1 2
3 2 2 2 1 2
3 3 3 3 2 2
3 3 3 3 2 2
3 2 2 2 2 2
3 2 2 2 2 2
 */