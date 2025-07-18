package SWExpert.SCore;

import java.util.*;
import java.io.*;

public class Solution {

    static int r, c, p, t, R, C;
    static int[][] map;
    static int[][] rotation;
    static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
    static int time = 0;
    static int mouseR, mouseC;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        R = r * p;
        C = c * p;
        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    mouseR = i;
                    mouseC = j;
                }
            }
        }

        rotation = new int[r][c];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                rotation[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (time = 0; time <= t; time++) {
            if (time > 0) rotateBlocks();
            if (time == t) break;

            Cheese target = findNearestCheese();
            if (target == null) continue;

            List<int[]> path = bfs(mouseR, mouseC, target.r, target.c);
            if (path == null || path.size() - 1 > t - time) break;

            for (int[] step : path.subList(1, path.size())) {
                time++;
                if (time > t) break;
                if (time % rotation[step[0]/p][step[1]/p] == 0) rotateBlocks();
                mouseR = step[0];
                mouseC = step[1];
                if (time == t) break;
            }

            if (time <= t && mouseR == target.r && mouseC == target.c) {
                map[mouseR][mouseC] = 0;
            }
            if (time == t) break;
        }

        System.out.println(mouseR + " " + mouseC);
    }

    static void rotateBlocks() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (time % rotation[i][j] == 0) rotateBlock(i * p, j * p);
            }
        }
    }

    static void rotateBlock(int sr, int sc) {
        int[][] temp = new int[p][p];
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < p; j++) {
                temp[j][p - 1 - i] = map[sr + i][sc + j];
            }
        }
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < p; j++) {
                map[sr + i][sc + j] = temp[i][j];
            }
        }
    }

    static Cheese findNearestCheese() {
        PriorityQueue<Cheese> pq = new PriorityQueue<>();
        for (int d = 0; d < 8; d++) {
            int nr = mouseR + dr[d];
            int nc = mouseC + dc[d];
            while (isIn(nr, nc) && map[nr][nc] != -1) {
                if (map[nr][nc] >= 2 && map[nr][nc] <= 6) {
                    pq.offer(new Cheese(nr, nc, map[nr][nc], Math.abs(mouseR - nr) + Math.abs(mouseC - nc)));
                    break;
                }
                nr += dr[d];
                nc += dc[d];
            }
        }
        return pq.isEmpty() ? null : pq.poll();
    }

    static List<int[]> bfs(int sr, int sc, int tr, int tc) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        int[][][] parent = new int[R][C][2];
        q.add(new int[]{sr, sc});
        visited[sr][sc] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == tr && cur[1] == tc) {
                LinkedList<int[]> path = new LinkedList<>();
                int[] node = cur;
                while (node[0] != sr || node[1] != sc) {
                    path.addFirst(node);
                    node = parent[node[0]][node[1]];
                }
                path.addFirst(new int[]{sr, sc});
                return path;
            }
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if (isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] != -1) {
                    visited[nr][nc] = true;
                    parent[nr][nc] = cur;
                    q.add(new int[]{nr, nc});
                }
            }
        }
        return null;
    }

    static boolean isIn(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    static class Cheese implements Comparable<Cheese> {
        int r, c, val, dist;
        Cheese(int r, int c, int val, int dist) {
            this.r = r; this.c = c; this.val = val; this.dist = dist;
        }
        public int compareTo(Cheese o) {
            if (this.dist != o.dist) return this.dist - o.dist;
            if (this.r != o.r) return this.r - o.r;
            return this.c - o.c;
        }
    }
}
