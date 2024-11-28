package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2609 {
    // https://www.acmicpc.net/problem/2609

    public static int gcd(int a, int b) {
        int n;

        while (b != 0) {
            n = a % b;
            a = b;
            b = n;
        }
        return a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int temp;
        if (a < b) {
            temp = a;
            a = b;
            b = temp;
        }

        int ans1 = gcd(a, b);
        a = a / ans1;
        b = b / ans1;
        if (a % b == 0) {
            System.out.println(ans1 + "\n" + (a / b * ans1));
        } else {
            System.out.println(ans1 + "\n" + (a * b * ans1));
        }
    }
}
