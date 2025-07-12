package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Q5653 {
    static class Pair implements Comparable<Pair> {
        int x, y, c;

        public Pair(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pair p = (Pair) obj;
            return this.x == p.x && this.y == p.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public int compareTo(Pair o) {
            return o.c - this.c;
        }
    }

    private static int tc, N, M, K;
    private static List<Set<Pair>> timeList = new ArrayList<>();
    private static Set<Pair> cells;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            timeList = new ArrayList<>();
            for (int i = 0; i <= K + 10; i++) {
                timeList.add(new HashSet<>());
            }
            cells = new HashSet<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    if (num > 0) {
                        timeList.get(num).add(new Pair(i, j, num));
                        cells.add(new Pair(i, j));
                    }
                }
            }

            simulate();

            int sum = 0;
            for (int i = 0; i <= K + 10; i++) {
                System.out.print("k: " + i + "\t\t");
                for (Pair p : timeList.get(i)) {
                    System.out.print(" (" + p.x + ", " + p.y + ", " + p.c + ")\t");
                    if ( i + p.c > K && i - p.c <= K) {
//                    if (i + 2 * p.c >= K) {
//                        System.out.print(" (" + p.x + ", " + p.y + ", " + p.c + ")\t");
                        sum++;
                    }
                }
                System.out.println();
            }

            sb.append("#").append(t).append(" ").append(sum).append("\n");
        }
        System.out.println(sb);
    }

    private static void simulate() {
//        System.out.println("===============simulate=============");
        for (int t = 1; t <= K; t++) {
            // t가 활성상태.
            Set<Pair> pairs = timeList.get(t);
            List<Pair> collect = pairs.stream().collect(Collectors.toList());
            // t시간에 활성상태인 세포를 생명력 순으로 내림차순
            Collections.sort(collect);

            for (Pair p : collect) {
                int nextTime = p.c + t + 1;
                for (int idx = 0; idx < 4; idx++) {
                    int nx = p.x + dx[idx];
                    int ny = p.y + dy[idx];

//                    System.out.print("nowTime: " + t + "\tnextTime: " + nextTime + " x: " + nx + ", y: " + ny + "\t");

                    Pair nextCell = new Pair(nx, ny, p.c);
                    if (!cells.contains(nextCell)) {
//                        System.out.print("\t add!");
                        cells.add(nextCell);
                        timeList.get(nextTime).add(nextCell);
                    }
//                    System.out.println();
                }
            }
        }
//        System.out.println("===============simulate=============");
    }
}
/*
3
2 2 10
1 1
0 2
5 5 19
3 2 0 3 0
0 3 0 0 0
0 0 0 0 0
0 0 1 0 0
0 0 0 0 2
9 10 37
0 0 0 0 0 0 0 0 3 0
0 0 0 0 0 0 0 0 5 3
0 0 2 0 0 0 0 4 0 0
3 0 0 0 0 0 4 0 0 0
0 0 0 0 0 3 5 0 0 2
0 0 0 0 0 0 0 0 0 5
0 0 0 0 0 0 0 0 2 3
0 0 0 0 0 0 0 0 0 0
0 0 2 2 0 0 0 0 0 0
 */

/*
import java.io.*;
import java.util.*;

public class Q5653_Optimized {
    static class Cell implements Comparable<Cell> {
        int x, y, life, start, end;

        public Cell(int x, int y, int life, int start) {
            this.x = x;
            this.y = y;
            this.life = life;
            this.start = start;           // 활성화 시점
            this.end = start + life;      // 사망 시점
        }

        @Override
        public int compareTo(Cell o) {
            return o.life - this.life; // 생명력 높은 순
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Cell) {
                Cell o = (Cell) obj;
                return x == o.x && y == o.y;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static int T, N, M, K;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Set<String> occupied; // 위치 점유 여부

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            occupied = new HashSet<>();
            PriorityQueue<Cell> pq = new PriorityQueue<>();
            Queue<Cell> allCells = new LinkedList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int life = Integer.parseInt(st.nextToken());
                    if (life > 0) {
                        int x = i + K; // 충분한 범위 확보를 위해 offset 줌
                        int y = j + K;
                        Cell c = new Cell(x, y, life, life + 1); // life 시간 뒤 활성화
                        pq.offer(c);
                        allCells.add(c);
                        occupied.add(x + "," + y);
                    }
                }
            }

            for (int time = 1; time <= K; time++) {
                int size = pq.size();
                PriorityQueue<Cell> next = new PriorityQueue<>();

                while (!pq.isEmpty()) {
                    Cell c = pq.poll();
                    if (c.start == time) {
                        for (int dir = 0; dir < 4; dir++) {
                            int nx = c.x + dx[dir];
                            int ny = c.y + dy[dir];
                            String key = nx + "," + ny;

                            if (!occupied.contains(key)) {
                                Cell newCell = new Cell(nx, ny, c.life, time + c.life + 1);
                                next.offer(newCell);
                                allCells.add(newCell);
                                occupied.add(key);
                            }
                        }
                    }

                    // 살아 있는 세포는 계속 유지
                    if (c.end > time) {
                        next.offer(c);
                    }
                }

                pq = next;
            }

            int count = 0;
            for (Cell c : allCells) {
                if (c.end > K) count++;
            }

            sb.append("#").append(sb.length() / 2 + 1).append(" ").append(count).append("\n");
        }

        System.out.println(sb);
    }
}

 */