package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q7576 {
    static class Tomato {
        int x, y;

        public Tomato(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int N, M;
    private static int[][] basket;
    private static boolean[][] visited;
    private static Queue<Tomato> tomatoes;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        basket = new int[M][N];
        visited = new boolean[M][N];
        tomatoes = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                basket[i][j] = Integer.parseInt(st.nextToken());
                if (basket[i][j] == 1) {
                    tomatoes.add(new Tomato(i, j));
                }
            }
        }

        int day = 0;
        while (tomatoes.size() != 0) {
            Queue<Tomato> nextDay = new LinkedList<>();
            day++;
//            System.out.println(day);
            while (!tomatoes.isEmpty()) {
                Tomato t = tomatoes.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nextX = t.x + dx[dir];
                    int nextY = t.y + dy[dir];
                    if (canGo(nextX, nextY)) {
                        basket[nextX][nextY] = day;
                        visited[nextX][nextY] = true;
                        nextDay.add(new Tomato(nextX, nextY));
                    }
                }
            }
            tomatoes = nextDay;
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && basket[i][j] == 0) {
                    System.out.println(-1);
                    System.exit(0);
                }
            }
        }
        System.out.println(day - 1);
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }

    private static boolean canGo(int x, int y) {
        if (!inRange(x, y)) return false;
        if (basket[x][y] != 0 || visited[x][y]) return false;
        return true;
    }
}
/*
6 4
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 1
---
8

6 4
0 -1 0 0 0 0
-1 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 1
---
-1

6 4
1 -1 0 0 0 0
0 -1 0 0 0 0
0 0 0 0 -1 0
0 0 0 0 -1 1
---
6
 */

//class Tomato {
//    int x, y, day;
//
//    public Tomato(int x, int y, int day) {
//        this.x = x;
//        this.y = y;
//        this.day = day;
//    }
//}
//
//
//public class Q7576 {
//
////    private static int N, M;
////    private static int[][] tomato, s;
////    private static boolean[][] visited;
////    private static ArrayList<Pair> arrayList = new ArrayList<>();
////    private static int[] dx = {-1, 0, 1, 0};
////    private static int[] dy = {0, 1, 0, -1};
////
////    public static boolean inRange(int x, int y) {
////        return 0 <= x && x < N && 0 <= y && y < M;
////    }
////
////    public static boolean canGo(int x, int y, int day) {
////        if(!inRange(x, y)) return false;
////        if (!visited[x][y] && tomato[x][y] == -1) {
////            s[x][y] = -1;
////            visited[x][y] = true;
////        }
////        if(tomato[x][y] != 0 || s[x][y] <= day) return false;
////        return true;
////    }
//
////    public static void bfs(int x, int y){
////        Queue<Tomato> q = new LinkedList<>();
////
////        for (int i = 0; i < arrayList.size(); i++) {
////            Pair cur = arrayList.get(i);
////            q.add(new Tomato(cur.x, cur.y, 0));
////            s[cur.x][cur.y] = 0;
////            visited[cur.x][cur.y] = true;
////        }
////
////        while (!q.isEmpty()) {
////            Tomato cur = q.poll();
////
////            for (int i = 0; i < 4; i++) {
////                int nextX = cur.x + dx[i];
////                int nextY = cur.y + dy[i];
////                int day = cur.day + 1;
////
////                if (canGo(nextX, nextY, day)) {
////                    q.add(new Tomato(nextX, nextY, day));
////                    s[nextX][nextY] = day;
////                    visited[nextX][nextY] = true;
////                }
////            }
////        }
////
////    }
//
//    static class Pair {
//        int x, y;
//
//        public Pair(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//    }
//
//    private static int N, M, tomatoCnt = 0, time = 0;
//    private static int[][] basket;
//    private static List<Pair> tomatoes = new ArrayList<>();
//    private static int[] dx = {-1, 0, 1, 0};
//    private static int[] dy = {0, 1, 0, -1};
//    public static void main(String[] args) throws IOException {
////        prev();
//        may13();
//    }
//
//    private static void may13() throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        M = Integer.parseInt(st.nextToken());
//        N = Integer.parseInt(st.nextToken());
//        basket = new int[N][M];
//        int noTomatoCnt = 0;
//
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < M; j++) {
//                basket[i][j] = Integer.parseInt(st.nextToken());
//                if (basket[i][j] == 1) {
//                    tomatoCnt++;
//                    tomatoes.add(new Pair(i, j));
//                } else if (basket[i][j] == -1) {
//                    noTomatoCnt++;
//                }
//            }
//        }
//
//        bfs();
//
//        System.out.println(tomatoCnt == (N * M - noTomatoCnt) ? time : -1);
//    }
//
//    private static void bfs() {
//        boolean[][] visited = new boolean[N][M];
//        Queue<Pair> q = new LinkedList<>();
//        for (Pair tomato : tomatoes) {
//            int x = tomato.x;
//            int y = tomato.y;
//            q.add(tomato);
//            visited[x][y] = true;
//        }
//        int size = tomatoes.size();
//        List<Pair> nextTomato = new ArrayList<>();
//
//        while (!q.isEmpty()) {
//            Pair tomato = q.poll();
//            int x = tomato.x;
//            int y = tomato.y;
//
//            for (int idx = 0; idx < 4; idx++) {
//                int nx = x + dx[idx];
//                int ny = y + dy[idx];
//                if (canGo(nx, ny, visited)) {
//                    nextTomato.add(new Pair(nx, ny));
//                    visited[nx][ny] = true;
//                    basket[nx][ny] = 1;
//                    tomatoCnt++;
//                }
//            }
//
//            if (q.size() == 0 && nextTomato.size() != 0) {
//                time++;
//                q.addAll(nextTomato);
//                nextTomato = new ArrayList<>();
//            }
//        }
//    }
//
//    private static boolean canGo(int x, int y, boolean[][] visited) {
//        if (!inRange(x, y)) return false;
//        if (visited[x][y] || basket[x][y] == -1) return false;
//        return true;
//    }
//
//    private static boolean inRange(int x, int y) {
//        return 0 <= x && x < N && 0 <= y && y < M;
//    }
//
////    private static void prev() throws IOException {
////        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
////        StringTokenizer st = new StringTokenizer(br.readLine());
////        M = Integer.parseInt(st.nextToken());
////        N = Integer.parseInt(st.nextToken());
////        tomato = new int[N][M];
////        s = new int[N][M];
////        visited = new boolean[N][M];
////        for (int i = 0; i < N; i++) {
////            st = new StringTokenizer(br.readLine());
////            for (int j = 0; j < M; j++) {
////                tomato[i][j] = Integer.parseInt(st.nextToken());
////                s[i][j] = N * M;
////                if (tomato[i][j] == 1) {
////                    arrayList.add(new Pair(i, j));
////                }
////            }
////        }
////
////        for (int i = 0; i < N; i++) {
////            for (int j = 0; j < M; j++) {
////                if (tomato[i][j] == 1 && !visited[i][j]) {
////                    bfs(i, j);
////                } else if (tomato[i][j] == -1) {
////                    visited[i][j] = true;
////                    s[i][j] = -1;
////                }
////            }
////        }
////
////        for (int i = 0; i < N; i++) {
////            for (int j = 0; j < M; j++) {
////                System.out.print(s[i][j] + " ");
////            }
////            System.out.println();
////        }
////
////        int ans = -1;
////        for (int i = 0; i < N; i++) {
////            for (int j = 0; j < M; j++) {
////                if (!visited[i][j]) {
////                    System.out.println(-1);
////                    System.exit(0);
////                }
////                ans = Math.max(ans, s[i][j]);
////            }
////        }
////        System.out.println(ans);
////    }
//}
