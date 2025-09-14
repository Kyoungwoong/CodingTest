package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q5565 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int change;
    public static void main(String[] args) throws IOException {
        change = Integer.parseInt(br.readLine());

        for (int i = 0; i < 9; i++) {
            change -= Integer.parseInt(br.readLine());
        }

        System.out.println(change);
    }
}
