package Baekjoon.Set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Q11478 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        HashSet<String> substr = new HashSet<>();
        int len = S.length();
        for (int i = 1; i <= len; i++) {
            // 문자열 길이 i
            for (int j = 0; j < len - i + 1; j++) {
                substr.add(S.substring(j, j + i));
            }
        }
//        for (String sub: substr) {
//            System.out.print(sub + " ");
//        }
//        System.out.println();
        System.out.println(substr.size());

    }
}
