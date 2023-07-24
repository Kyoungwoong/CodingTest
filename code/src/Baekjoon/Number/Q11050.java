package Baekjoon.Number;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11050 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int k = K;
        int result = 1;
        int bot = 1;
        for (int i = 0; i < K; i++) {
            result *= N;
            N--;
            bot *= k;
            k--;
        }
        System.out.println(result/bot);
    }
}
