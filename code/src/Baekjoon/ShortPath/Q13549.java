package Baekjoon.ShortPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q13549 {

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
    private static int N, K, INF = Integer.MAX_VALUE;
    private static int[] d = new int[100001];
    private static PriorityQueue<Hide> pq = new PriorityQueue<>();

    private static void dijkstra(int start) {
        d[start] = 0;
        pq.add(new Hide(start, 0));

        while (!pq.isEmpty()) {
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

//            System.out.println(cur.pos + " " + cur.time);

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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>();
        Arrays.fill(d, Integer.MAX_VALUE);
        dijkstra(N);


    }
}
