package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q13549 {
    // https://www.acmicpc.net/problem/13549
    private static class Hide implements Comparable<Hide> {
        int pos, time;

        public Hide(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }

        @Override
        public int compareTo(Hide hide) {
            return this.time - hide.time;
        }
    }
    private static int N, K;
    private static int[] d = new int[100001];
    private static PriorityQueue<Hide> pq = new PriorityQueue<>();

    private static void dijkstra(int start) {
        d[start] = 0;
        pq.add(new Hide(start, 0));

        while(true) {
            Hide cur = pq.poll();
            if (cur.pos > 100000 || cur.pos < 0) {
                continue;
            }

            if (d[cur.pos] < cur.time) {
                continue;
            } else if (d[cur.pos] == cur.time && start != cur.pos) {
                continue;
            }

            if (cur.pos == K) {
                System.out.println(cur.time);
                return;
            }

            if (d[cur.pos] > cur.time || (d[cur.pos] == cur.time && cur.pos == start)) {
                d[cur.pos] = cur.time;
                if (cur.pos != 0) {
                    pq.add(new Hide(cur.pos * 2, cur.time));
                }
                pq.add(new Hide(cur.pos - 1, cur.time + 1));
                pq.add(new Hide(cur.pos + 1, cur.time + 1));
            }
        }
    }

    private static int[] visited = new int[100001];

    public static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        visited[N] = 1;

        int ans = Integer.MAX_VALUE;
        int cnt = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == K) {
                System.out.println(visited[cur] - 1);
                return;
            }

            for (int next : new int[]{cur * 2, cur - 1, cur + 1}) {
                if (next < 0 || next > 100000) continue;

                if (visited[next] == 0) {
                    if (next == cur * 2) {
                        visited[next] = visited[cur];
                    } else {
                        visited[next] = visited[cur] + 1;
                    }
                    queue.add(next);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>();
        Arrays.fill(d, Integer.MAX_VALUE);
        dijkstra(N);
        bfs();
    }
}
