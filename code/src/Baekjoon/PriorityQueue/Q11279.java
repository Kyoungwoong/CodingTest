package Baekjoon.PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Q11279 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int q = Integer.parseInt(br.readLine());
            switch (q) {
                case 0:
                    if (pq.isEmpty()) {
                        sb.append(0 + "\n");
                    } else {
                        sb.append(pq.poll() + "\n");
                    }
                    break;
                default:
                    pq.add(q);
                    break;

            }
        }
        System.out.println(sb);
    }
}
