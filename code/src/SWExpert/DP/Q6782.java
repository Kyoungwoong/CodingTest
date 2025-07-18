package SWExpert.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q6782 {
    private static int tc;
    private static Long N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= tc; t++) {
            N = Long.parseLong(br.readLine());
            sb.append("#").append(t).append(" ").append(solve(N)).append("\n");
        }
        System.out.println(sb);
    }

    private static long solve(long n) {
        long count = 0;

        while (n != 2) {
            long sqrt = (long) Math.sqrt(n);
            if (sqrt * sqrt == n) {
                n = sqrt;
                count++; // 한 번의 연산
            } else {
                long nextSquare = (sqrt + 1) * (sqrt + 1);
                count += nextSquare - n;
                n = nextSquare;
            }
        }

        return count;
    }
}
