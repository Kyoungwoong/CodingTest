package Baekjoon.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q15683 {
    static class CCTV {
        int x, y, num;

        public CCTV(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    private static int N, M;
    private static List<CCTV> cctvs = new ArrayList<>();
    private static int[][] office;
    private static boolean[][] visited;
    private static int ans = Integer.MAX_VALUE;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int[][][] directions = {
            {}, // index 0 dummy
            {{0}, {1}, {2}, {3}}, // 1번 CCTV
            {{0, 2}, {1, 3}},     // 2번 CCTV
            {{0, 1}, {1, 2}, {2, 3}, {3, 0}}, // 3번
            {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}}, // 4번
            {{0, 1, 2, 3}} // 5번
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        office = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                office[i][j] = Integer.parseInt(st.nextToken());
                if (office[i][j] >= 1 && office[i][j] <= 5) {
                    cctvs.add(new CCTV(i, j, office[i][j]));
                }
            }
        }

        dfs(0, office);
        System.out.println(ans);
    }

    public static void dfs(int cnt, int[][] temp) {
        if (cnt == cctvs.size()) {
            int cur = calculate(temp);
            ans = Math.min(ans, cur);
            return;
        }

        /**
         * 1. cnt에 해당하는 cctv 가져오기
         * 2. temp 배열 채우기
         * 3. 다음 cnt+1하기 dfs(cnt+1, temp)
         * 4. 원복하기
         * 5. 90도 돌려서 1번 경우로 돌아가기
         */
        CCTV cctv = cctvs.get(cnt); // 1
        for (int idx = 0; idx < directions[cctv.num].length; idx++) { // 5
            int[][] next = deepCopy(temp);
            next = canDetection(cctv, directions[cctv.num][idx], next); // 2
//            System.out.println("===============(" + cctv.x + ", " +cctv.y + ")===========");
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < M; j++) {
//                    System.out.print(next[i][j] + " ");
//                }
//                System.out.println();
//            }
            dfs(cnt + 1, next); // 3
        }
    }

    public static int[][] deepCopy(int[][] src) {
        int[][] dest = new int[src.length][src[0].length];
        for (int i = 0; i < src.length; i++) {
            dest[i] = src[i].clone();
        }
        return dest;
    }


    public static int[][] canDetection(CCTV cctv, int[] dir, int[][] temp) {
        int size = dir.length;
        for (int i = 0; i < size; i++) {
            int x = cctv.x, y = cctv.y;
            while (canGo(x, y)) {
                if (temp[x][y] == 0) {
                    temp[x][y] = 7;
                }
                x += dx[dir[i]];
                y += dy[dir[i]];
            }
        }
        return temp;
    }

    public static int calculate(int[][] temp) {
        int safeArea = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (temp[i][j] == 0) {
                    safeArea++;
                }
            }
        }
        return safeArea;
    }

    public static boolean canGo(int x, int y) {
        if (!inRange(x, y)) return false;
        if (visited[x][y] || office[x][y] == 6) return false;
        return true;
    }

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
