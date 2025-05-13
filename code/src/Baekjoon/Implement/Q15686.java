package Baekjoon.Implement;

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
    private static int N, M, ans = Integer.MAX_VALUE;
    private static int[][] map;
    private static List<Pair> houses = new ArrayList<>();
    private static List<Pair> stores = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
//        List<Pair> stores = new ArrayList<>();

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

        dfs(0, stores.size());

        System.out.println(ans);
    }

    private static Set<Integer> excludeSet = new HashSet<>();
    private static void dfs(int start, int cnt) {
        int size = stores.size();

        if (cnt == M) {
            int sum = 0;
            for (Pair house : houses) {
                int houseChickenD = Integer.MAX_VALUE;
                for (int i = 0; i < size; i++) {
                    if (excludeSet.contains(i)) {
                        continue;
                    }
                    Pair store = stores.get(i);
                    int chickenD = Math.abs(house.x - store.x) +
                            Math.abs(house.y - store.y);
                    houseChickenD = Math.min(houseChickenD, chickenD);
                }
                sum += houseChickenD;
            }
            ans = Math.min(ans, sum);
//            for (int excludeIdx : excludeSet) {
//                System.out.print(excludeIdx + " ");
//            }
//            System.out.println();
            return;
        }

        // 제외될 리스트
        for (int i = start; i < size; i++) {
            if (excludeSet.contains(i)) {
                continue;
            }
            excludeSet.add(i);
            dfs(i + 1, cnt - 1);
            excludeSet.remove(i);
        }
    }

}
