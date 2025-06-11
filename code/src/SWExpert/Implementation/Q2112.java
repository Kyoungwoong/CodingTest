package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2112 {
    private static int tc, D,W, K, ans;
    private static int[][] secure;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            secure = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    secure[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ans = Integer.MAX_VALUE;

            for (int r = 0; r <= D; r++) {
                dfs(0, 0, r);
                if (ans != Integer.MAX_VALUE) {
                    break;
                }
            }

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

//    private static void dfs(int cnt, int limit, boolean[] visited) {
//        if (cnt == limit) {
//            if (check()) {
//                ans = limit;
//            }
//            return;
//        }
//
//        if (ans != Integer.MAX_VALUE) {
//            return;
//        }
//
//        for (int i = 0; i < D; i++) {
//            if (visited[i]) continue;
//
//            visited[i] = true;
//            int[] temp = new int[W];
//            System.arraycopy(secure[i], 0, temp, 0, W);
//            // i 행을 A로 만들기
//            for (int w = 0; w < W; w++) {
//                secure[i][w] = 0;
//            }
//            dfs(cnt + 1, limit, visited);
//
//            // i행을 B로 만들기
//            for (int w = 0; w < W; w++) {
//                secure[i][w] = 1;
//            }
//            dfs(cnt + 1, limit, visited);
//
//            // 원복
//            for (int w = 0; w < W; w++) {
//                secure[i][w] = temp[w];
//            }
//
//            visited[i] = false;
//        }
//    }

    private static void dfs(int start, int cnt, int limit) {
        if (cnt == limit) {
            if (check()) {
                ans = limit;
            }
            return;
        }

        for (int i = start; i < D; i++) {
            int[] temp = Arrays.copyOf(secure[i], W);

            // A
            Arrays.fill(secure[i], 0);
            dfs(i + 1, cnt + 1, limit);

            // B
            Arrays.fill(secure[i], 1);
            dfs(i + 1, cnt + 1, limit);

            // rollback
            secure[i] = temp;
        }
    }

    private static boolean check() {
        for (int c = 0; c < W; c++) {
            int count = 1;
            for (int r = 1; r < D; r++) {
                if (secure[r][c] == secure[r - 1][c]) {
                    count++;
                    if (count >= K) {
                        break;
                    }
                } else {
                    count = 1;
                }
            }
            if (count < K) {
                return false;
            }
        }
        return true;
    }
}
/*
10
6 8 3
0 0 1 0 1 0 0 1
0 1 0 0 0 1 1 1
0 1 1 1 0 0 0 0
1 1 1 1 0 0 0 1
0 1 1 0 1 0 0 1
1 0 1 0 1 1 0 1
6 8 3
1 1 1 1 0 0 1 0
0 0 1 1 0 1 0 1
1 1 1 1 0 0 1 0
1 1 1 0 0 1 1 0
1 1 0 1 1 1 1 0
1 1 1 0 0 1 1 0
6 8 4
1 1 0 0 0 1 1 0
1 0 1 0 0 1 1 1
0 1 0 0 1 1 0 0
1 0 1 0 0 0 0 0
1 1 0 0 0 0 0 0
1 0 0 0 1 1 1 1
6 4 4
1 1 0 0
0 1 0 1
0 0 0 1
1 1 1 1
1 1 0 1
1 0 1 0
6 10 3
0 1 0 0 0 1 0 0 1 1
0 1 1 0 0 1 0 0 1 0
0 1 0 0 1 0 1 1 1 1
0 0 0 0 0 1 1 1 1 0
0 1 0 0 1 1 1 1 1 1
1 0 0 0 1 1 0 0 1 1
6 6 5
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
6 6 4
1 1 1 1 1 1
0 0 0 0 0 1
0 1 1 1 0 1
0 1 0 1 0 1
0 1 0 0 0 1
0 1 1 1 1 1
8 15 3
0 1 1 0 0 1 1 0 1 1 0 0 0 0 0
1 0 0 0 1 1 0 0 0 0 0 1 0 1 1
1 1 0 1 0 1 0 1 0 1 0 1 0 0 0
0 1 1 1 0 0 1 0 0 0 0 1 0 0 0
0 0 0 0 0 0 1 0 0 0 1 1 0 0 1
1 0 1 0 0 1 0 1 1 1 1 0 1 1 1
0 0 0 0 0 1 1 1 0 0 0 0 0 1 0
0 0 1 0 1 1 0 1 1 0 0 0 1 0 0
10 20 4
1 0 1 1 1 1 1 1 1 1 0 0 1 1 1 0 1 1 0 1
1 1 0 1 1 1 0 0 1 0 0 0 1 1 1 1 0 0 1 0
1 1 0 1 1 0 0 0 1 1 1 1 1 0 0 1 1 0 1 0
0 0 0 1 1 0 0 0 0 1 0 0 1 0 1 1 1 0 1 0
0 1 1 0 1 0 1 0 1 0 0 1 0 0 0 0 1 1 1 1
1 0 1 0 1 0 1 1 0 0 0 0 1 1 1 0 0 0 0 0
0 1 0 0 1 1 0 0 0 0 0 1 1 0 0 1 1 0 1 1
1 0 0 0 0 1 0 1 1 0 1 1 0 1 0 0 1 1 1 0
0 1 1 0 0 1 0 1 0 0 0 0 0 0 0 1 1 1 0 1
0 0 0 0 0 0 1 1 0 0 1 1 0 0 0 0 0 0 1 0
13 20 5
1 1 0 1 0 0 0 1 1 1 1 0 0 0 1 1 1 0 0 0
1 1 1 1 0 1 0 1 0 0 0 0 1 0 0 0 0 1 0 0
1 0 1 0 1 1 0 1 0 1 1 0 0 0 0 1 1 0 1 0
0 0 1 1 0 1 1 0 1 0 0 1 1 0 0 0 1 1 1 1
0 0 1 0 0 1 0 0 1 0 0 0 0 1 0 0 0 0 1 1
0 0 1 0 0 0 0 0 0 0 0 0 1 1 1 0 0 1 0 1
0 0 0 1 0 0 0 0 0 0 1 1 0 0 0 1 0 0 1 0
1 1 1 0 0 0 1 0 0 1 1 1 0 1 0 1 0 0 1 1
0 1 1 1 1 0 0 0 1 1 0 1 0 0 0 0 1 0 0 1
0 0 0 0 1 0 1 0 0 0 1 0 0 0 0 1 1 1 1 1
0 1 0 0 1 1 0 0 1 0 0 0 0 1 0 1 0 0 1 0
0 0 1 1 0 0 1 0 0 0 1 0 1 1 0 1 1 1 0 0
0 0 0 1 0 0 1 0 0 0 1 0 1 1 0 0 1 0 1 0

 */