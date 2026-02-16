package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1855 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static String encoded;
    static int K;
    static char[][] encodedMap;

    public static void main(String[] args) throws IOException {
        K = Integer.parseInt(br.readLine());
        encoded = br.readLine();
        int len = encoded.length();

        encodedMap = new char[len / K + 1][K];

        for (int idx = 0; idx < len; idx++) {
            if ((idx / K) % 2 == 1) {
                encodedMap[idx / K][K - 1 - (idx % K)] = encoded.charAt(idx);
            } else {
                encodedMap[idx / K][idx % K] = encoded.charAt(idx);
            }
        }

        for (int col = 0; col < K; col++) {
            for (int row = 0; row < len / K + 1; row++) {
                if (encodedMap[row][col] < 'a' || encodedMap[row][col] > 'z') break;
                sb.append(encodedMap[row][col]);
            }
        }
        System.out.println(sb.toString());
    }
}
