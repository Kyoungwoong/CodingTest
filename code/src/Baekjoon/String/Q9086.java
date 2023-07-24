package Baekjoon.String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9086 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            String cmd = br.readLine();
            int len = cmd.length();
            sb.append(cmd.charAt(0) +""+ cmd.charAt(len - 1)+"\n");
        }
        System.out.println(sb);
    }
}
