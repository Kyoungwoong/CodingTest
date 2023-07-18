package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2745 {
    private static String N;
    private static int B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = st.nextToken();
        B = Integer.parseInt(st.nextToken());

        int a = 0;
        for (int i = 0; i < N.length(); i++) {
            int std = 0;
            if (N.charAt(i) >= 'A' && N.charAt(i) <= 'Z') {
                std = N.charAt(i) - 'A' + 10;
            }
            if (N.charAt(i) >= '0' && N.charAt(i) <= '9') {
                std = N.charAt(i) - '0' + 0;
            }
            a = a*B + std;
        }
        System.out.println(a);
    }
}
