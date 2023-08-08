package Baekjoon.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q2346 {
    private static class Balloon {
        int idx, cmd;

        public Balloon(int idx, int cmd) {
            this.idx = idx;
            this.cmd = cmd;
        }
    }
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Deque<Balloon> dq = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            dq.add(new Balloon(i, Integer.parseInt(st.nextToken())));
        }

        StringBuilder sb = new StringBuilder();
        while (dq.size() > 1) {
            Balloon cur = dq.pollFirst();
            sb.append(cur.idx + " ");
            int cmd = cur.cmd;
            if (cmd < 0) {
                cmd = -1 * cmd;
                for (int i = 0; i < cmd; i++) {
                    dq.addFirst(dq.pollLast());
                }
            } else {
                for (int i = 0; i < cmd -1; i++) {
                    dq.add(dq.pollFirst());
                }
            }
        }
        sb.append(dq.poll().idx);
        System.out.println(sb);

//        while (!dq.isEmpty()) {
//            System.out.println(dq.poll().idx); // 1 2 3 4 5
//        }
    }
}
