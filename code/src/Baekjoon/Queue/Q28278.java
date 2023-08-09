package Baekjoon.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q28278 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Stack<Integer> s = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            switch (cmd) {
                case 1:
                    int num = Integer.parseInt(st.nextToken());
                    s.add(num);
                    break;
                case 2:
                    if (s.isEmpty()) {
                        sb.append("-1\n");
                    } else {
                        sb.append(s.pop() + "\n");
                    }
                    break;
                case 3:
                    sb.append(s.size() + "\n");
                    break;
                case 4:
                    if (s.isEmpty()) {
                        sb.append("1\n");
                    } else {
                        sb.append("0\n");
                    }
                    break;
                case 5:
                    if (s.isEmpty()) {
                        sb.append("-1\n");
                    } else {
                        sb.append(s.peek() + "\n");
                    }
                    break;
            }
        }
        System.out.println(sb);
    }
}
