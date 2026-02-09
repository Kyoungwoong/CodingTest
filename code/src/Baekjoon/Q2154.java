package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2154 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder cmd = new StringBuilder();
    static int N;

    public static void main(String[] args) throws IOException {
        String target = br.readLine();
        int len = target.length();
        N = Integer.parseInt(target);

        int num = 1;
        int pos = 0;
        while (true) {
            if (cmd.length() == len) {
                break;
            }

            cmd.append(num++);
        }

        if (cmd.length() >= len && cmd.substring(0, len).equals(target)) {
            System.out.println(pos + 1);
            return;
        }

        while (num <= N) {
            String s = Integer.toString(num++);
            cmd.append(s);

            while (cmd.length() >= len) {
                if (cmd.toString().substring(0, len).equals(target)) {
                    System.out.println(pos + 1);
                    return;
                } else {
                    cmd.deleteCharAt(0);
                    pos++;
                }
            }
        }
    }
}
