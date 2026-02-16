package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1816 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static long S;

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine());

        for (int test = 1; test <= testCase; test++) {
            S = Long.parseLong(br.readLine());

            boolean flag = true;
            for (int s = 2; s <= 1000000; s++) {
                if (S % s == 0) {
                    flag = false;
                    sb.append("NO");
                    break;
                }
            }

            if (flag) {
                sb.append("YES");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
