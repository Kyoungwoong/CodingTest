package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q15686 {
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int N, M;
    private static int[][] map;
    private static List<Pair> stores = new ArrayList<>();
    private static List<Pair> houses = new ArrayList<>();
    private static Stack<Pair> temp = new Stack<>();
    private static int minChickenD = Integer.MAX_VALUE;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    stores.add(new Pair(i, j));
                } else if (map[i][j] == 1) {
                    houses.add(new Pair(i, j));
                }
            }
        }

        visited = new boolean[stores.size()];

        // M개 골라서 치킨 거리 구하기
        dfs(0, 0);

        System.out.println(minChickenD);
    }

    public static void dfs(int start, int cnt) {
        if (cnt == M) {
            int totalDist = 0;

            for (Pair house : houses) {
                int minDist = Integer.MAX_VALUE;
                for (Pair store : temp) {
                    minDist = Math.min(minDist, getChickenDistance(store, house));
                }
                totalDist += minDist;
            }

//            for (Pair store : temp) {
//                System.out.println("store.x = " + store.x + " store.y: " + store.y);
//            }
//            System.out.println("totalDist = " + totalDist + "\n");
            minChickenD = Math.min(minChickenD, totalDist);
            return;
        }

        for (int i = start; i < stores.size(); i++) {
            temp.add(stores.get(i));
            dfs(i + 1, cnt + 1);
            temp.pop();
        }
    }

    public static int getChickenDistance(Pair store, Pair house) {
        return Math.abs(store.x - house.x) + Math.abs(store.y - house.y);
    }
}
