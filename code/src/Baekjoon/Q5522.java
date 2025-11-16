package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q5522 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        long ans = 0;

        for (int i = 0; i < 5; i++) {
            ans += Integer.parseInt(br.readLine());
        }

        System.out.println(ans);
    }
}
