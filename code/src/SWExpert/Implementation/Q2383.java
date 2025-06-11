package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Q2383 {
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Time implements Comparable<Time> {
        int peopleIdx, startTime;

        public Time(int peopleIdx, int startTime) {
            this.peopleIdx = peopleIdx;
            this.startTime = startTime;
        }

        @Override
        public int compareTo(Time o) {
            return Integer.compare(this.startTime, o.startTime);
        }
    }

    static int N, minTime;
    static List<Pair> people, stairs;
    static int[] stairLength;
    static List<Integer> selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= tc; t++) {
            N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            people = new ArrayList<>();
            stairs = new ArrayList<>();
            stairLength = new int[2];
            selected = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        people.add(new Pair(i, j));
                    } else if (map[i][j] >= 2) {
                        stairs.add(new Pair(i, j));
                        stairLength[stairs.size() - 1] = map[i][j];
                    }
                }
            }

            minTime = Integer.MAX_VALUE;
            int[] selected = new int[people.size()];
            Arrays.fill(selected, 2);
            dfs(0, selected);
            sb.append("#").append(t).append(" ").append(minTime).append("\n");
        }

        System.out.print(sb);
    }

    private static void dfs(int personIdx, int[] selected) {
        if (personIdx == people.size()) {
            getTime(selected);
            return;
        }

        for (int i = 0; i < 2; i++) {
            selected[personIdx] = i;
            dfs(personIdx + 1, selected);
            selected[personIdx] = 2;
        }
    }

    private static void getTime(int[] selected) {
        List<PriorityQueue<Time>> stairsList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            stairsList.add(new PriorityQueue<>());
        }
        for (int i = 0; i < people.size(); i++) {
            Pair person = people.get(i);
            Pair stair = stairs.get(selected[i]);
            int arriveTime = Math.abs(person.x - stair.x) + Math.abs(person.y - stair.y) + 1;
            stairsList.get(selected[i]).add(new Time(i, arriveTime));
        }

        // 계단 시간 구하기
        int time = Integer.MIN_VALUE;
        for (int i = 0; i < 2; i++) {
            time = Math.max(time, processStair(stairsList.get(i), stairLength[i]));
        }

        minTime = Math.min(minTime, time);
    }

    private static int processStair(PriorityQueue<Time> arrival, int stairTime) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 계단에서 끝나는 시간
        int time = 1;

        for (Time arrive : arrival) {
            int arriveTime = arrive.startTime;
            while (!pq.isEmpty() && pq.peek() <= arriveTime) {
                pq.poll();
            }

            if (pq.size() < 3) {
                pq.add(arriveTime + stairTime);
            } else {
                int waitUntil = pq.poll();
                pq.add(waitUntil + stairTime);
            }
        }

        while (!pq.isEmpty()) {
            time = pq.poll();
        }

        return time;
    }

}
/*
10
5
0 1 1 0 0
0 0 1 0 3
0 1 0 1 0
0 0 0 0 0
1 0 5 0 0
5
0 0 1 1 0
0 0 1 0 2
0 0 0 1 0
0 1 0 0 0
1 0 5 0 0
6
0 0 0 1 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 1 0 0 0 0
2 0 1 0 0 0
0 0 2 0 0 0
6
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
1 0 0 0 0 0
0 0 0 2 0 4
7
0 0 0 0 0 0 0
0 0 0 0 0 0 4
0 0 0 0 1 0 0
1 0 0 1 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 2 0 0 0 0 0
7
0 0 0 0 0 0 0
7 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
2 0 0 0 0 1 0
0 0 0 0 0 0 0
8
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 2
0 0 0 0 0 0 0 0
2 0 0 0 0 0 0 0
0 0 0 0 0 1 0 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 1 0
0 0 0 0 1 0 0 0
8
3 0 0 0 0 0 5 0
0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
1 0 1 1 0 0 0 0
0 0 0 0 0 0 1 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
9
0 0 0 1 0 0 0 0 0
0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 8
7 0 0 0 0 1 0 0 0
0 0 0 0 0 1 1 0 0
0 0 0 0 0 0 0 0 0
1 0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
10
0 0 0 0 0 0 0 0 0 0
0 0 0 0 1 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
3 0 1 0 1 0 0 0 0 2
1 1 0 0 1 0 1 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
 */