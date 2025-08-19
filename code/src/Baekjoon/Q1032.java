package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1032 {
    static int N;
    static String std = null;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            if (std == null) {
                std = br.readLine();
            } else {
                int len = std.length();
                StringBuilder sb = new StringBuilder(len);
                String cur = br.readLine();
                for (int k = 0; k < len; k++) {
                    if (std.charAt(k) == cur.charAt(k)) {
                        sb.append(std.charAt(k));
                    } else {
                        sb.append("?");
                    }
                }
                std = sb.toString();
            }
        }

        System.out.println(std);
    }
}
