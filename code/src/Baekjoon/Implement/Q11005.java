package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11005 {
    private static int N, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        String cmd = "";
        while (N > 0) {
            if (N % B >= 10 && N % B <= 35) {
                cmd = (char)('A' + (N % B -10)) + cmd;
            } else {
                cmd = (N % B) + cmd;
            }
            N /= B;
        }
        System.out.println(cmd);
    }
}
