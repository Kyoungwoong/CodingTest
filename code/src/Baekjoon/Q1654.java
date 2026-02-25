package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1654 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, K;
    static int[] lans;

    public static void main(String[] args) throws IOException {
        input();
        long result = solve();
        System.out.println(result);
    }

    private static long solve() {
        long count = 0;
        long left = 1, right = lans[K - 1];

        if (N == 1) {
            return right;
        }

        while(left <= right) {
            long mid = (left + right) / 2;
            int lanCount = calculate(mid);

//            System.out.println("mid: " + mid + "\t" + lanCount);
            if (lanCount >= N) {
                count = Math.max(count, mid);
                left = mid + 1;
            } else {
                right = mid -1;
            }
        }
        return count;
    }

    private static int calculate(long length) {
        int result = 0;

        for (int lan : lans) {
            result += (lan) / length;
        }

        return result;
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        lans = new int[K];
        for (int i = 0; i < K; i++) {
            lans[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(lans);
    }
}
