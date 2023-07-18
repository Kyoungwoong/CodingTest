package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q10988 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String test = br.readLine();
        int len = test.length();
        boolean check = true;
        for (int i = 0; i < len / 2; i++) {
            if (test.charAt(i) != test.charAt(len - i - 1)) {
                check = false;
            }
        }
        if (check) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
