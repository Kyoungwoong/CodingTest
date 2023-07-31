package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Tomato {
    int x, y, day;

    public Tomato(int x, int y, int day) {
        this.x = x;
        this.y = y;
        this.day = day;
    }
}


public class Q7576 {

    private static int N, M;
    private static int[][] tomato, s;
    private static boolean[][] visited;
    private static ArrayList<Pair> arrayList = new ArrayList<>();
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static boolean canGo(int x, int y, int day) {
        if(!inRange(x, y)) return false;
        if (!visited[x][y] && tomato[x][y] == -1) {
            s[x][y] = -1;
            visited[x][y] = true;
        }
        if(tomato[x][y] != 0 || s[x][y] <= day) return false;
        return true;
    }

    public static void bfs(int x, int y){
        Queue<Tomato> q = new LinkedList<>();

        for (int i = 0; i < arrayList.size(); i++) {
            Pair cur = arrayList.get(i);
            q.add(new Tomato(cur.x, cur.y, 0));
            s[cur.x][cur.y] = 0;
            visited[cur.x][cur.y] = true;
        }

        while (!q.isEmpty()) {
            Tomato cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                int day = cur.day + 1;

                if (canGo(nextX, nextY, day)) {
                    q.add(new Tomato(nextX, nextY, day));
                    s[nextX][nextY] = day;
                    visited[nextX][nextY] = true;
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        tomato = new int[N][M];
        s = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                tomato[i][j] = Integer.parseInt(st.nextToken());
                s[i][j] = N * M;
                if (tomato[i][j] == 1) {
                    arrayList.add(new Pair(i, j));
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tomato[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j);
                } else if (tomato[i][j] == -1) {
                    visited[i][j] = true;
                    s[i][j] = -1;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(s[i][j] + " ");
            }
            System.out.println();
        }

        int ans = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    System.out.println(-1);
                    System.exit(0);
                }
                ans = Math.max(ans, s[i][j]);
            }
        }
        System.out.println(ans);

    }
}
