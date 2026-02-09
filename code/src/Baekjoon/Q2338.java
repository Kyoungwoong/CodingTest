package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Q2338 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static BigInteger A, B;

    public static void main(String[] args) throws IOException {
        A = new BigInteger(br.readLine());
        B = new BigInteger(br.readLine());

        sb.append(A.add(B)).append("\n");
        sb.append(A.subtract(B)).append("\n");
        sb.append(A.multiply(B)).append("\n");

        System.out.println(sb);
    }
}
