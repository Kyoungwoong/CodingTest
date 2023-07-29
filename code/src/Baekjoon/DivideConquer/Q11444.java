package Baekjoon.DivideConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11444 {
    private static final int threshold = 16, MOD = 1000000007;// 임계값


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        long N = Long.parseLong(st.nextToken());

        if(N == 1 || N == 0) {
            System.out.println(N);
            return;
        }

        long[][] A = {{1, 1}, {1, 0}};

        // 분할정복 메소드 호출
        long[][] C = {{1, 0}, {0, 1}};

        while (N > 0) {
            if (N % 2 == 1) {
                C = multiply(C, A);
            }
            A = multiply(A, A);
            N /= 2;
        }

        System.out.println(C[0][1]);
    }

    public static long[][] multiply(long[][] o1, long[][] o2) {

        long[][] ret = new long[2][2];

        ret[0][0] = ((o1[0][0] * o2[0][0]) + (o1[0][1] * o2[1][0])) % MOD;
        ret[0][1] = ((o1[0][0] * o2[0][1]) + (o1[0][1] * o2[1][1])) % MOD;
        ret[1][0] = ((o1[1][0] * o2[0][0]) + (o1[1][1] * o2[1][0])) % MOD;
        ret[1][1] = ((o1[1][0] * o2[0][1]) + (o1[1][1] * o2[1][1])) % MOD;

        return ret;
    }
}
