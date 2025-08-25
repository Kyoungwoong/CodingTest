package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q10808 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String word = br.readLine();

        int[] alphabet = new int[26];

        int len = word.length();
        for (int idx = 0; idx < len; idx++) {
            alphabet[word.charAt(idx) - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < 26; idx++) {
            sb.append(alphabet[idx]).append(" ");
        }
        System.out.println(sb);
    }
}
