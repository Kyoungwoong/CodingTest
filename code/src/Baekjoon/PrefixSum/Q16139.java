package Baekjoon.PrefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q16139 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        String S = br.readLine();
        int len = S.length();
        int[][] prefixSum = new int[26][len];
        prefixSum[S.charAt(0)-'a'][0] = 1;
        for (int i = 0; i < 26; i++) {
            char std = (char)('a' + i);
            for (int j = 1; j < len; j++) {
                if (S.charAt(j) == std) {
                    prefixSum[i][j] = prefixSum[i][j - 1] + 1;
                } else {
                    prefixSum[i][j] = prefixSum[i][j - 1];
                }
            }
        }

//        for (int i = 0; i < 26; i++) {
//            for (int j = 0; j < len; j++) {
//                System.out.print(prefixSum[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            char std = st.nextToken().charAt(0);
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            if (l == 0) {
                sb.append(prefixSum[std - 'a'][r] - prefixSum[std - 'a'][0]);
            } else {
                sb.append(prefixSum[std - 'a'][r] - prefixSum[std - 'a'][l-1]);
            }

            sb.append("\n");
        }
        System.out.println(sb);
    }
}
