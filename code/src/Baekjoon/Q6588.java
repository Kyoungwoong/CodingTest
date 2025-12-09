package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q6588 {
    private static final int SIZE = 1000000;
    private static boolean[] isPrime = new boolean[SIZE + 1];

    public static void main(String[] args) throws IOException {
        makePrime();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }
            boolean ok = false;
            int half = n / 2;

            // 홀수만 검사
            for (int a = 3; a <= half; a += 2) {
                if (isPrime[a] && isPrime[n - a]) {
                    sb.append(n).append(" = ")
                            .append(a).append(" + ")
                            .append(n - a)
                            .append('\n');
                    ok = true;
                    break;
                }
            }

            if (!ok) {
                sb.append("Goldbach's conjecture is wrong.\n");
            }
        }
        System.out.println(sb.toString());
    }

    private static void makePrime() {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= SIZE; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= SIZE; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }
}
