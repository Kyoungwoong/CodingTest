package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q11721 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String cmd;

    public static void main(String[] args) throws IOException {
        cmd = br.readLine();

        StringBuilder sb = new StringBuilder();
        while (cmd.length() >= 10) {
            sb.append(cmd.substring(0, 10)).append("\n");
            cmd = cmd.substring(10);
        }
        sb.append(cmd);
        System.out.println(sb);

    }
}
