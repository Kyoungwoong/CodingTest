package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q5554 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int minute, second = 0;

    public static void main(String[] args) throws IOException {
        for (int idx = 0; idx < 4; idx++) {
            second += Integer.parseInt(br.readLine());
        }

        minute = second / 60;
        second %= 60;

        System.out.println(minute);
        System.out.println(second);
    }
}
