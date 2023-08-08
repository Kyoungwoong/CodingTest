package Baekjoon.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q28279 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        Deque<Integer> dq = new LinkedList<>();
        int N = Integer.parseInt(br.readLine());
        int x = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            switch (cmd) {
                case 1:
                    x = Integer.parseInt(st.nextToken());
                    dq.addLast(x);
                    break;
                case 2:
                    x = Integer.parseInt(st.nextToken());
                    dq.addFirst(x);
                    break;
                case 3:
                    if (dq.isEmpty()) {
                        sb.append("-1\n");
                    } else {
                        sb.append(dq.pollLast() + "\n");
                    }
                    break;
                case 4:
                    if (dq.isEmpty()) {
                        sb.append("-1\n");
                    } else {
                        sb.append(dq.pollFirst() + "\n");
                    }
                    break;
                case 5:
                    sb.append(dq.size() + "\n");
                    break;
                case 6:
                    if (dq.isEmpty()) {
                        sb.append("1\n");
                    } else {
                        sb.append("0\n");
                    }
                    break;
                case 7:
                    if (dq.isEmpty()) {
                        sb.append("-1\n");
                    } else {
                        sb.append(dq.peekLast() + "\n");
                    }
                    break;
                case 8:
                    if (dq.isEmpty()) {
                        sb.append("-1\n");
                    } else {
                        sb.append(dq.peekFirst() + "\n");
                    }
                    break;
            }
        }
        System.out.println(sb);
    }
}
