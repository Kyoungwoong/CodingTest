package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q14502 {
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int[][] lab;
    private static boolean[][] visited;
    private static List<Pair> walls = new ArrayList<>();
    private static List<Pair> virus = new ArrayList<>();
    private static int M, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        lab = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if (lab[i][j] == 0) {
                    walls.add(new Pair(i, j));
                } else if (lab[i][j] == 2) {
                    virus.add(new Pair(i, j));
                }
            }
        }

        int size = walls.size();
        int max = -1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < j; k++) {
                    lab[walls.get(i).x][walls.get(i).y] = 1;
                    lab[walls.get(j).x][walls.get(j).y] = 1;
                    lab[walls.get(k).x][walls.get(k).y] = 1;

                    max = Math.max(max, spread());

                    lab[walls.get(i).x][walls.get(i).y] = 0;
                    lab[walls.get(j).x][walls.get(j).y] = 0;
                    lab[walls.get(k).x][walls.get(k).y] = 0;
                }
            }
        }
        System.out.println(max);
    }

    public static int spread() {
        Queue<Pair> q = new LinkedList<>();
        int[][] temp = deepCopy(lab);
        visited = new boolean[M][N];
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        for (Pair now : virus) {
            visited[now.x][now.y] = true;
            q.add(now);
        }

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                if (canGo(nextX, nextY, temp)) {
                    visited[nextX][nextY] = true;
                    temp[nextX][nextY] = 2;
                    q.add(new Pair(nextX, nextY));
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (temp[i][j] == 0) {
                    ans++;
                }
            }
        }

        return ans;
    }

    public static boolean canGo(int x, int y, int[][] arr) {
        if (x < 0 || x >= M || y < 0 || y >= N) return false;
        if(visited[x][y] || arr[x][y] != 0) return false;
        return true;
    }

    public static int[][] deepCopy(int[][] arr) {
        int[][] copy = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i].clone();
        }
        return copy;
    }
}
