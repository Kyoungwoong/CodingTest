package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.math.BigInteger;

public class Q1247 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        for (int test = 1; test <= 3; test++) {
            int N = Integer.parseInt(br.readLine());
            BigInteger sum = BigInteger.ZERO;

            // BigInteger는 불변(immutable) 이라서 add()가 원본을 바꾸지 않아요.
            for (int token = 0; token < N; token++) {
                sum = sum.add(new BigInteger(br.readLine().trim()));
            }

            // 방법 1
//            if (sum.compareTo(BigInteger.ZERO) == -1) {
//                sb.append("-");
//            } else if (sum.compareTo(BigInteger.ZERO) == 0) {
//                sb.append("0");
//            } else {
//                sb.append("+");
//            }
//            sb.append("\n");
            // 방법 2
            int s = sum.signum();
            if (s < 0) sb.append('-');
            else if (s == 0) sb.append('0');
            else sb.append('+');
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }
}
