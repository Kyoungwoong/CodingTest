package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q15964 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static long A, B;

    public static void main(String[] args) throws IOException {
        input();
        long ans = solve(A, B);
        System.out.println(ans);
    }

    private static long solve(long a, long b) {
        return (a + b) * (a - b);
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
    }
}
