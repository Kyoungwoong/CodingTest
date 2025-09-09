package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q31403 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int A, B, C;

    public static void main(String[] args) throws IOException {
        A = Integer.parseInt(br.readLine());
        B = Integer.parseInt(br.readLine());
        C = Integer.parseInt(br.readLine());

        sb.append(A + B - C).append("\n");
        sb.append(Integer.parseInt("" + A + B) - C);
        System.out.println(sb.toString());
    }
}
