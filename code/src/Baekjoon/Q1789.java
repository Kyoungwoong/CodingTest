package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1789 {
    // https://www.acmicpc.net/problem/1789
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long S = Long.parseLong(br.readLine());

        int ans = 0;
        long sum = 0, num = 1;

        while (sum < S) {
            sum += num;
            ans++;
            num++;
        }

        if (sum > S) {
            ans--;
        }

        System.out.println(ans);
    }
}
