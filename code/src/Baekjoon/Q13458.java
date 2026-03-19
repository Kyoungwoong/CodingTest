package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q13458 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, B, C;
    static int[] students;

    public static void main(String[] args) throws IOException {
        input();

        long ans = N;
        for (int student : students) {
            int remain = student - B;
            if (remain > 0) {
                ans += (remain / C);
                if (remain % C != 0) {
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        students = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            students[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
    }
}
