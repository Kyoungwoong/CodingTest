package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q10797 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int date;

    public static void main(String[] args) throws IOException {
        date = Integer.parseInt(br.readLine());

        int ans = 0;
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < 5; idx++) {
            int num = Integer.parseInt(st.nextToken());
            if (num % 10 == date) {
                ans++;
            }
        }

        System.out.println(ans);
    }
}
