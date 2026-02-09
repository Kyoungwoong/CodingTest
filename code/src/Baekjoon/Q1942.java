package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1942 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        for (int test = 0; test < 3; test++) {
            st = new StringTokenizer(br.readLine());

            String[] clockArray = st.nextToken().split(":");
            Clock start = new Clock(clockArray[0], clockArray[1], clockArray[2]);

            clockArray = st.nextToken().split(":");
            Clock end = new Clock(clockArray[0], clockArray[1], clockArray[2]);

            int ans = solve(start, end);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static int solve(Clock start, Clock end) {
        int result = 0;

        while (true) {
            if (start.isTriple()) {
                result++;
            }

            if (start.equals(end)) {
                break;
            }


            start.add();
        }

        return result;
    }

    static class Clock {
        int h, m, s;

        public Clock(String h, String m, String s) {
            this.h = Integer.parseInt(h);
            this.m = Integer.parseInt(m);
            this.s = Integer.parseInt(s);
        }

        public boolean equals(Clock other) {
            if (this.h == other.h &&
                    this.m == other.m &&
                    this.s == other.s) {
                return true;
            }

            return false;
        }

        public boolean isTriple() {
            String time = "" + h + m + s;
            if ((Integer.parseInt(time)) % 3 == 0) {
                return true;
            }
            return false;
        }

        public void add() {
            s++;

            if (s == 60) {
                s = 0;
                m++;
            }
            if (m == 60) {
                m = 0;
                h++;
            }
            if (h == 24) {
                h = 0;
            }
        }
    }
}

/**
 * 최적화
 * package Baekjoon;
 *
 * import java.io.*;
 * import java.util.StringTokenizer;
 *
 * public class Q1942 {
 *     static final int DAY = 24*60*60; // 86400
 *     static int[] pref = new int[DAY]; // pref[i] = 00:00:00 ~ i초까지 '좋은 시각' 개수
 *
 *     static int toSec(int h, int m, int s) { return h*3600 + m*60 + s; }
 *
 *     static boolean good(int h, int m, int s) {
 *         int sum = h/10 + h%10 + m/10 + m%10 + s/10 + s%10;
 *         return sum % 3 == 0;
 *     }
 *
 *     static {
 *         int cnt = 0;
 *         for (int sec = 0; sec < DAY; sec++) {
 *             int h = sec / 3600;
 *             int m = (sec / 60) % 60;
 *             int s = sec % 60;
 *             if (good(h, m, s)) cnt++;
 *             pref[sec] = cnt;
 *         }
 *     }
 *
 *     static int rangeCount(int startSec, int endSec) { // 양끝 포함
 *         if (startSec <= endSec) {
 *             return pref[endSec] - (startSec == 0 ? 0 : pref[startSec - 1]);
 *         } else { // 자정 넘김
 *             int left = pref[DAY - 1] - (startSec == 0 ? 0 : pref[startSec - 1]);
 *             int right = pref[endSec];
 *             return left + right;
 *         }
 *     }
 *
 *     public static void main(String[] args) throws Exception {
 *         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 *         StringBuilder sb = new StringBuilder();
 *
 *         for (int t = 0; t < 3; t++) {
 *             StringTokenizer st = new StringTokenizer(br.readLine());
 *             String[] A = st.nextToken().split(":");
 *             String[] B = st.nextToken().split(":");
 *             int sh = Integer.parseInt(A[0]), sm = Integer.parseInt(A[1]), ss = Integer.parseInt(A[2]);
 *             int eh = Integer.parseInt(B[0]), em = Integer.parseInt(B[1]), es = Integer.parseInt(B[2]);
 *             int s = toSec(sh, sm, ss);
 *             int e = toSec(eh, em, es);
 *             sb.append(rangeCount(s, e)).append('\n');
 *         }
 *         System.out.print(sb);
 *     }
 * }
 */
