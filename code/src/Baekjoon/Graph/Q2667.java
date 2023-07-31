package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Q2667 {
    private static int N, cnt = 0;
    private static int[][] arr;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static boolean[][] visited;
    private static HashMap<Integer, Integer> apartment = new HashMap<>();
    private static Queue<Pair> q = new LinkedList<>();

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    public static void bfs(int row, int col) {
        q.add(new Pair(row, col));
        visited[row][col] = true;
        int house = 1;

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                if (isRange(nextX, nextY) && !visited[nextX][nextY] && arr[nextX][nextY] == 1) {
                    house++;
                    q.add(new Pair(nextX, nextY));
                    visited[nextX][nextY] = true;
                }
            }
        }
        apartment.put(cnt, house);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 1 && !visited[i][j]) {
                    cnt++;
                    bfs(i, j);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(cnt + "\n");
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= cnt; i++) {
            ans.add(apartment.get(i));
        }
        Collections.sort(ans);
        for (int i = 0; i < ans.size(); i++) {
            sb.append(ans.get(i) + "\n");
        }
        System.out.println(sb);
    }
}
