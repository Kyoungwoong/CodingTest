package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q16916 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String P = br.readLine();

        System.out.println(kmp(S, P) ? 1 : 0);
    }

    // KMP 문자열 검색 함수
    public static boolean kmp(String text, String pattern) {
        int[] lps = buildLPS(pattern);
        int i = 0; // text의 인덱스
        int j = 0; // pattern의 인덱스

        while (i < text.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;

                // 패턴이 완전히 매칭됨
                if (j == pattern.length()) {
                    return true;
                }
            } else {
                if (j != 0) {
                    j = lps[j - 1]; // LPS를 참고해 패턴 이동
                } else {
                    i++;
                }
            }
        }
        return false;
    }

    // LPS 배열 생성
    public static int[] buildLPS(String pattern) {
        int[] lps = new int[pattern.length()];
        int j = 0; // 접두사 길이
        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = lps[j - 1]; // LPS를 참고해 접두사 길이 감소
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
                lps[i] = j;
            }
        }
        return lps;
    }
}

