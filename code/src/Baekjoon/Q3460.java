package Baekjoon;

//    https://www.acmicpc.net/problem/3460

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Q3460 {
    public static void binaryNum(int n, List<Integer> binary) {
        while (n > 0) {
            binary.add(n % 2);
            n /= 2;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            List<Integer> binary = new ArrayList<>();
            binaryNum(n, binary);
            for (int i = 0; i < binary.size(); i++) {
                if (binary.get(i) == 1) {
                    sb.append(i + " ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
