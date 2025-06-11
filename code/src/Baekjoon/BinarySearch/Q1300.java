package Baekjoon.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1300 {

    private static int N, k;
    public static void main(String[] args) throws IOException {
//        prev();
        may26();
    }

    private static void may26() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        int low = 1, high = k;
        int answer = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            long count = countLessEqual(mid);

            if (count >= k) {
                answer = mid;  // mid는 정답 후보
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        System.out.println(answer);
    }

    private static long countLessEqual(int x) {
        long count = 0;
        for (int i = 1; i <= N; i++) {
            count += Math.min(x / i, N);
        }
        return count;
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        long lo = 1;
        long hi = k;

        // lower-bound
        while(lo < hi) {

            long mid = (lo + hi) / 2;
            long count = 0;

            for(int i = 1; i <= N; i++) {
                count += Math.min(mid / i, N);
            }

            if(k <= count) {
                hi = mid;
            }
            else {
                lo = mid + 1;
            }
        }

        System.out.println(lo);
    }
}
