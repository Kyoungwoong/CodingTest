package Baekjoon.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q12789 {
    private static int N;
    private static Queue<Integer> cur = new LinkedList<>();
    private static Stack<Integer> space = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cur.add(Integer.parseInt(st.nextToken()));
        }

        int idx = 1;
        while (!cur.isEmpty()) {
            int now = cur.peek();
            if (idx == now) {
                idx++;
                cur.poll();
                continue;
            } else {
                if (space.size() == 0 || idx != space.peek()) {
                    space.add(cur.poll());
                } else {
                    idx++;
                    space.pop();
                }
            }
        }

        while (!space.isEmpty()) {
            int now = space.peek();
            if (idx == now) {
                idx++;
                space.pop();
            } else {
                System.out.println("Sad");
                System.exit(0);
            }
        }
        System.out.println("Nice");

    }
}
/*
10
5 4 3 2 1 6 7 8 9 10

5
5 2 3 1 4
 */
