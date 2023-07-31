package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Dot {
    int x, y, crash;

    public Dot(int x, int y, int crash) {
        this.x = x;
        this.y = y;
        this.crash = crash;
    }
}

public class Q2206 {
    private static int N, M, ans = Integer.MAX_VALUE;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int[][] map;
    private static int[][] s;
    private static boolean[][][] visited;
    private static Queue<Dot> q;

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static void bfs(){
        q = new LinkedList<>();
        q.add(new Dot(0, 0, 0));

        while (!q.isEmpty()) {
            Dot cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                int crash = cur.crash;

                if (!inRange(nextX, nextY)) {
                    continue;
                }

                if (map[nextX][nextY] == 1) {
                    if(crash == 0){
                        visited[crash][nextX][nextY] = true;
                        s[nextX][nextY] = s[cur.x][cur.y] + 1;
                        q.offer(new Dot(nextX, nextY, 1));
                    }
                } else {
                    if(!visited[cur.crash][nextX][nextY]){
                        visited[crash][nextX][nextY] = true;
                        s[nextX][nextY] = s[cur.x][cur.y] + 1;
                        q.offer(new Dot(nextX, nextY, crash));
                    }
                }

                if(nextX == N-1 && nextY == M-1){
                    System.out.print(s[nextX][nextY] + 1);
                    System.exit(0);
                }
            }
        }
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(s[i][j] + " ");
//            }
//            System.out.println();
//        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if(N-1 == 0 && M-1 == 0){
            System.out.print(1);
            System.exit(0);
        }

        map = new int[N][M];
        visited = new boolean[2][N][M];
        s = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        bfs();

        System.out.println(-1);

    }
}
