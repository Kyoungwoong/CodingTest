package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q5644 {
    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class AP implements Comparable<AP> {
        int idx, performance;

        public AP(int idx, int performance) {
            this.idx = idx;
            this.performance = performance;
        }

        @Override
        public int compareTo(AP o) {
            if (this.performance == o.performance) {
                return this.idx - o.idx;
            }
            return o.performance - this.performance;
        }
    }

    private static int tc, M, A, N = 10;
    private static int[] dx = {0, -1, 0, 1, 0};
    private static int[] dy = {0, 0, 1, 0, -1};
    private static Pos personA, personB;
    private static List<Integer> moveA, moveB;
    private static Map<Integer, Integer> performance = new HashMap<>();
    private static Map<Integer, Set<Integer>> coverage = new HashMap();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            // A의 이동 정보
            personA = new Pos(0, 0);
            moveA = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i <= M; i++) {
                if (i == 0) {
                    moveA.add(0);
                } else {
                    moveA.add(Integer.parseInt(st.nextToken()));
                }
            }

            // B의 이동 정보
            personB = new Pos(N - 1, N - 1);
            moveB = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i <= M; i++) {
                if (i == 0) {
                    moveB.add(0);
                } else {
                    moveB.add(Integer.parseInt(st.nextToken()));
                }

            }

            // AP
            performance = new HashMap<>();
            coverage = new HashMap();
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken()) - 1;
                int x = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                coverage.put(i, new HashSet<>());
                for (int s = x - c; s <= x + c; s++) {
                    for (int j = y - c; j <= y + c; j++) {
                        if (inRange(s, j)
                                && Math.abs(s - x) + Math.abs(j - y) <= c) {
                            coverage.get(i).add(s * N + j);
                        }
                    }
                }

                performance.put(i, p);
            }
//            printInit();
            int sumBC = simulate();
            sb.append("#").append(t).append(" ").append(sumBC).append("\n");
        }
        System.out.println(sb);
    }

    private static void printInit() {
        System.out.println("=============coverage===========");
        for (int key : coverage.keySet()) {
            Set<Integer> set = coverage.get(key);
            for (int num : set) {
                System.out.print(num + " ");
            }
            System.out.println();
        }

        System.out.println("=============performance===========");
        for (int key : performance.keySet()) {
            System.out.println("key: " + key + " performance: " + performance.get(key));
        }
    }

    private static int simulate() {
        int t = 0, charge = 0;
        int curAx = personA.x, curBx = personB.x;
        int curAy = personA.y, curBy = personB.y;

        while (t <= M) {
            curAx += dx[moveA.get(t)];
            curAy += dy[moveA.get(t)];
            curBx += dx[moveB.get(t)];
            curBy += dy[moveB.get(t)];

            int numA = curAx * N + curAy;
            int numB = curBx * N + curBy;

            List<AP> listA = new ArrayList<>();
            List<AP> listB = new ArrayList<>();

            for (int i = 0; i < A; i++) {
                if (coverage.get(i).contains(numA)) {
                    listA.add(new AP(i, performance.get(i)));
                }
                if (coverage.get(i).contains(numB)) {
                    listB.add(new AP(i, performance.get(i)));
                }
            }

            Collections.sort(listA);
            Collections.sort(listB);

            int max = 0;

            if (listA.isEmpty() && listB.isEmpty()) {
                max = 0;
            } else if (listA.isEmpty()) {
                max = listB.get(0).performance;
            } else if (listB.isEmpty()) {
                max = listA.get(0).performance;
            } else {
                for (AP a : listA) {
                    for (AP b : listB) {
                        if (a.idx == b.idx) {
                            max = Math.max(max, a.performance); // 공유할 경우 하나만 받음
                        } else {
                            max = Math.max(max, a.performance + b.performance);
                        }
                    }
                }
            }

            charge += max;
            t++;
        }

        return charge;
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}

/*
5
20 3
2 2 3 2 2 2 2 3 3 4 4 3 2 2 3 3 3 2 2 3
4 4 1 4 4 1 4 4 1 1 1 4 1 4 3 3 3 3 3 3
4 4 1 100
7 10 3 40
6 3 2 70
40 2
0 3 0 3 3 2 2 1 0 4 1 3 3 3 0 3 4 1 1 3 2 2 2 2 2 0 2 3 2 2 3 4 4 3 3 3 2 0 4 4
0 1 0 3 4 0 4 0 0 1 1 1 0 1 4 4 4 4 4 3 3 3 0 1 0 4 3 2 1 4 4 3 2 3 2 2 0 4 2 1
5 2 4 140
8 3 3 490
60 4
0 3 3 3 0 1 2 2 2 1 2 2 3 3 4 4 0 3 0 1 1 2 2 3 2 2 3 2 2 0 3 0 1 1 1 4 1 2 3 3 3 3 3 1 1 4 3 2 0 4 4 4 3 4 0 3 3 0 3 4
1 1 4 1 1 1 1 1 1 4 4 1 2 2 3 2 4 0 0 0 4 3 3 4 3 3 0 1 0 4 3 0 4 3 2 3 2 1 2 2 3 4 0 2 2 1 0 0 1 3 3 1 4 4 3 0 1 1 1 1
6 9 1 180
9 3 4 260
1 4 1 500
1 3 1 230
80 7
2 2 2 2 2 2 0 2 2 0 4 0 2 3 3 2 3 3 0 3 3 3 4 3 3 2 1 1 1 0 4 4 4 1 0 2 2 2 1 1 4 1 2 3 4 4 3 0 1 1 0 3 4 0 1 2 2 2 1 1 3 4 4 4 4 4 4 3 2 1 4 4 4 4 3 3 3 0 3 3
4 4 1 1 2 1 2 3 3 3 4 4 4 4 4 1 1 1 1 1 1 1 1 0 3 3 2 0 4 0 1 3 3 3 2 2 1 0 3 2 3 4 1 0 1 2 2 3 2 0 4 0 3 4 1 1 0 0 3 2 0 0 4 3 3 4 0 4 4 4 4 0 3 0 1 1 4 4 3 0
4 3 1 170
10 1 3 240
10 5 3 360
10 9 3 350
9 6 2 10
5 1 4 350
1 8 2 450
100 8
2 2 3 2 0 2 0 3 3 1 2 2 2 2 3 3 0 4 4 3 2 3 4 3 3 2 3 4 4 4 2 2 2 0 2 2 4 4 4 4 1 1 1 2 2 2 4 3 0 2 3 3 4 0 0 1 1 4 1 1 1 1 2 2 1 1 3 3 3 0 3 2 3 3 0 1 3 3 0 1 1 3 3 4 0 4 1 1 2 2 4 0 4 1 1 2 2 1 1 1
4 4 4 0 4 1 1 4 1 1 1 1 3 2 1 2 1 1 4 4 1 0 2 3 4 4 4 4 4 0 1 0 2 2 2 0 2 2 2 2 2 2 3 0 0 1 4 3 3 2 0 0 4 4 4 0 2 0 4 1 1 2 2 0 4 4 0 0 2 0 2 3 3 0 2 3 0 3 4 0 4 3 4 4 3 4 1 1 2 2 2 0 0 1 0 4 1 1 1 4
3 4 2 340
10 1 1 430
3 10 4 10
6 3 4 400
7 4 1 80
4 5 1 420
4 1 2 350
8 4 4 300

 */