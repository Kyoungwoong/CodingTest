package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q21608 {
    static class Seat implements Comparable<Seat> {
        int x, y, favoriteCnt, emptySeat;

        public Seat(int x, int y, int favoriteCnt, int emptySeat) {
            this.x = x;
            this.y = y;
            this.favoriteCnt = favoriteCnt;
            this.emptySeat = emptySeat;
        }

        @Override
        public int compareTo(Seat seat) {
            if (this.favoriteCnt == seat.favoriteCnt) {
                if (this.emptySeat == seat.emptySeat) {
                    if (this.x == seat.x) {
                        return this.y - seat.y;
                    }
                    return this.x - seat.x;
                }
                return seat.emptySeat - this.emptySeat;
            }
            return seat.favoriteCnt - this.favoriteCnt;
        }
    }

    private static int N;
    private static List<Integer> list = new ArrayList<>();
    private static Map<Integer, Set<Integer>> favoriteMap = new HashMap<>();
    private static int[][] classes;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        classes = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            Set favoriteSet = new HashSet<>();
            for (int j = 0; j < 4; j++) {
                favoriteSet.add(Integer.parseInt(st.nextToken()));
            }
            favoriteMap.put(key, favoriteSet);
            list.add(key);
        }
        // END INIT

        // 자리 앉히기
        for (int student : list) {
            List<Seat> candidates = getCandidates(student);
            Collections.sort(candidates);
            Seat s = candidates.get(0);
            classes[s.x][s.y] = student;
        }

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(classes[i][j] + " ");
//            }
//            System.out.println();
//        }

        // 만족도 구하기
        System.out.println(calcSatisfaction());
    }

    public static List<Seat> getCandidates(int student) {
        List<Seat> candidates = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (classes[i][j] != 0) continue;

                // 좋아하는 학생 구하기
                int favoriteCnt = 0;
                Set<Integer> favorites = favoriteMap.get(student);

                // 비어있는 칸 구하기
                int emptyCnt = 0;
                for (int dir = 0; dir < 4; dir++) {
                    int nextX = i + dx[dir];
                    int nextY = j + dy[dir];
                    if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N) {
                        if (classes[nextX][nextY] == 0) {
                            emptyCnt++;
                        } else {
                            if (favorites.contains(classes[nextX][nextY])) {
                                favoriteCnt++;
                            }
                        }
                    }
                }

                candidates.add(new Seat(i, j, favoriteCnt, emptyCnt));
            }
        }

        return candidates;
    }

    public static int calcSatisfaction() {
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int student = classes[i][j];
                Set<Integer> favorites = favoriteMap.get(student);
                int cnt = 0;
                for (int dir = 0; dir < 4; dir++) {
                    int nextX = i + dx[dir];
                    int nextY = j + dy[dir];
                    if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N) {
                        if (favorites.contains(classes[nextX][nextY])) {
                            cnt++;
                        }
                    }
                }
                if (cnt != 0) {
                    ans += Math.pow(10, cnt - 1);
                }
            }
        }

        return ans;
    }
}
