package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1333 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    /**
     * N: 앨범에 총 N곡
     * L: 모든 노래의 길이는 L초
     * D: D초마다 1초동안 울림
     * 노래와 노래사이 5초동안 아무 노래도 들리지 않는 조용한 구간 존재.
     */
    static int N, L, D;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        int albumEnd = N * L + (N - 1) * 5;

        /**
         * ( (L+0) ~ (L+5) ) * N 사이에 D가 있어야 됨.
         */
        for (int t = 0;; t += D) {
            // 앨범 끝난 후면 무조건 들림
            if (t >= albumEnd) {
                System.out.println(t);
                return;
            }

            // 앨범 재생 중이면, 노래 구간에 속하는지 확인
            boolean inSong = false;
            // 노래 i의 재생 구간: [i*(L+5), i*(L+5)+L)
            for (int i = 0; i < N; i++) {
                int start = i * (L + 5);
                if (t >= start && t < start + L) {
                    inSong = true;
                    break;
                }
            }

            if (!inSong) { // 노래가 아닌(=조용한) 구간이면 들림
                System.out.println(t);
                return;
            }
        }
    }
}
