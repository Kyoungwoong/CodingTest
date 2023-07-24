package Baekjoon.Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q25501 {
    private static int cnt = 1;
    public static int recursion(String s, int l, int r) {
        if (l >= r) {
            return 1;
        } else if (s.charAt(l) != s.charAt(r)) {
            return 0;
        } else {
            cnt++;
            return recursion(s, l + 1, r - 1);
        }
    }

    public static int isPalindrome(String s) {
        return recursion(s, 0, s.length() - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            cnt = 1;
            String test = br.readLine();
            sb.append(isPalindrome(test) + " " + cnt +"\n");
        }
        System.out.println(sb);
    }
}
