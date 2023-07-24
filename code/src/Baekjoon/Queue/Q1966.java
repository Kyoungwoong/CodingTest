package Baekjoon.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Doc implements Comparable<Doc> {
    int priority, idx;

    public Doc(int priority, int idx) {
        this.priority = priority;
        this.idx = idx;
    }

    @Override
    public int compareTo(Doc doc) {
        return this.priority - doc.priority;
    }
}

public class Q1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            Queue<Doc> q = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                q.add(new Doc(num, j));
                pq.add(num);
            }

            int order = 0;
            while (true) {
                int cur = pq.poll();
                Doc poll = q.poll();
                while (poll.priority != cur) {
                    q.add(poll);
                    poll = q.poll();
                }
                order++;
                if (poll.idx == M) {
                    break;
                }
            }
            sb.append(order + "\n");
        }
        System.out.println(sb);
    }
}
