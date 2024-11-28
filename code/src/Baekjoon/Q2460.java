package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2460 {
    // https://www.acmicpc.net/problem/2460
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = 10;

        int sum = 0;
        int answer = Integer.MIN_VALUE;
        while (testCase-- > 0) {
            st = new StringTokenizer(br.readLine());
            int out = Integer.parseInt(st.nextToken());
            int in = Integer.parseInt(st.nextToken());
            sum += (in - out);
            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }
}
