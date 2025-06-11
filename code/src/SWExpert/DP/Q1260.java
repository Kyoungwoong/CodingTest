package SWExpert.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1260 {
    static class Pair implements Comparable<Pair> {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getSize() {
            return this.x * this.y;
        }

        @Override
        public int compareTo(Pair pair) {
            if (this.getSize() == pair.getSize()) {
                return Integer.compare(this.x, pair.x);
            }

            return Integer.compare(this.getSize(), pair.getSize());
        }

        @Override
        public boolean equals(Object obj) {
            Pair pair = (Pair) obj;
            return this.x == pair.x && this.y == pair.y;
        }
    }

    private static int testCase, N;
    private static int[][] bottles;
    private static boolean[][] visited;
    private static List<Pair> matrices;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= testCase; t++) {
            N = Integer.parseInt(br.readLine());
            bottles = new int[N][N];
            visited = new boolean[N][N];
            matrices = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    bottles[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && bottles[i][j] != 0) {
                        bfs(i, j);
                    }
                }
            }

            sb.append("#").append(t).append(" ");

            matrices = reorderMatrices(matrices);

            // 행렬 최소 계산
            int minCost = matrixChainOrder();
            sb.append(minCost);
            if (t != testCase) {
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private static List<Pair> reorderMatrices(List<Pair> matrices) {
        List<Pair> ordered = new ArrayList<>();
        boolean[] visited = new boolean[matrices.size()];

        for (int i = 0; i < matrices.size(); i++) {
            List<Pair> temp = new ArrayList<>();
            boolean[] tempVisited = new boolean[matrices.size()];

            temp.add(matrices.get(i));
            tempVisited[i] = true;

            while (temp.size() < matrices.size()) {
                Pair last = temp.get(temp.size() - 1);
                boolean found = false;
                for (int j = 0; j < matrices.size(); j++) {
                    if (!tempVisited[j] && last.y == matrices.get(j).x) {
                        temp.add(matrices.get(j));
                        tempVisited[j] = true;
                        found = true;
                        break;
                    }
                }
                if (!found) break;
            }

            if (temp.size() == matrices.size()) return temp;
        }

        return new ArrayList<>(); // 연결 불가능한 경우
    }

    private static int matrixChainOrder() {
        int size = matrices.size();
        int[] dims = new int[size + 1];
        dims[0] = matrices.get(0).x;
        for (int i = 0; i < size; i++) {
            dims[i + 1] = matrices.get(i).y;
        }

        int[][] dp = new int[size][size];
        for (int len = 2; len <= size; len++) {
            for (int i = 0; i <= size - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + dims[i] * dims[k + 1] * dims[j + 1];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        return dp[0][size - 1];
    }

    private static void bfs(int x, int y) {
        visited[x][y] = true;
        Pair start = new Pair(x, y);
        Pair end = null;
        Queue<Pair> q = new LinkedList<>();
        q.add(start);

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        while (!q.isEmpty()) {
            end = q.poll();

            for (int idx = 0; idx < 4; idx++) {
                int nextX = end.x + dx[idx];
                int nextY = end.y + dy[idx];

                if (canGo(nextX, nextY)) {
                    visited[nextX][nextY] = true;
                    q.add(new Pair(nextX, nextY));
                }
            }
        }

        Pair matrix = new Pair(end.x - start.x + 1, end.y - start.y + 1);
        matrices.add(matrix);
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static boolean canGo(int x, int y) {
        if(!inRange(x, y)) return false;
        if(visited[x][y] || bottles[x][y] == 0) return false;

        return true;
    }
}
/*
2
9
1 1 3 2 0 0 0 0 0
3 2 5 2 0 0 0 0 0
2 3 3 1 0 0 0 0 0
0 0 0 0 4 5 5 3 1
1 2 5 0 3 6 4 2 1
2 3 6 0 2 1 1 4 2
0 0 0 0 4 2 3 1 1
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
4
1 2 0 0
0 0 0 0
9 4 2 0
1 7 3 0
 */
