package SWExpert.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1258 {
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
    }

    private static int testCase, N;
    private static int[][] bottles;
    private static boolean[][] visited;
    private static PriorityQueue<Pair> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= testCase; t++) {
            N = Integer.parseInt(br.readLine());
            bottles = new int[N][N];
            visited = new boolean[N][N];
            pq = new PriorityQueue<>();

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

            sb.append("#").append(t).append(" ").append(pq.size());
            while (!pq.isEmpty()) {
                Pair cur = pq.poll();
                sb.append(" ").append(cur.x).append(" ").append(cur.y);
            }
            if (t != testCase) {
                sb.append("\n");
            }
        }
        System.out.println(sb);
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

        pq.add(new Pair(end.x - start.x + 1, end.y - start.y + 1));
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
10
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