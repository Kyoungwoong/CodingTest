package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1673 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, K;

    public static void main(String[] args) throws IOException {

        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) break;

            st = new StringTokenizer(line);
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int chicken = n;
            int stamp = n;

            while (stamp >= k) {
                int q = stamp / k;
                int r = stamp % k;
                chicken += q;
                stamp = r + q;
            }
            sb.append(chicken).append('\n');
        }
        System.out.print(sb);
    }
}
