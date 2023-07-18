package Baekjoon.Implement;

import javax.swing.plaf.basic.BasicButtonUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q5086 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == 0 && b == 0) {
                System.out.println(sb);
                break;
            }
            if (a % b == 0) {
                sb.append("multiple\n");
            } else if (b % a == 0) {
                sb.append("factor\n");
            } else {
                sb.append("neither\n");
            }
        }
    }
}
