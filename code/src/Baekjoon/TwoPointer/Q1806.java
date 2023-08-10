package Baekjoon.TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1806 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int N = Integer.parseInt(st.nextToken());
//        int S = Integer.parseInt(st.nextToken());
//        int[] arr = new int[N + 1];
//        st = new StringTokenizer(br.readLine());
//        for (int i = 0; i < N; i++) {
//            arr[i] = Integer.parseInt(st.nextToken());
//        }
//
//        int sum = 0;
//        int right = 0;
//        int left = 0;
//        int ans = Integer.MAX_VALUE;
//
//        while (right <= N) {
//
//            if (sum >= S) { // 합이 s 이상!
//                sum -= arr[left++];
//                ans = Math.min(ans, right - left + 1);
//            } else if (sum < S) {
//                sum += arr[right++];
//            }
//        }
//
//        if (ans == Integer.MAX_VALUE) {
//            System.out.println(0);
//        } else {
//            System.out.println(ans);
//        }
//    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int j = 0;
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            while (j + 1 < N && sum + arr[j + 1] <= S) {
                sum += arr[j++];
            }

            if (sum >= S) {
                ans = Math.min(ans, j - i);
            } else if (j < N) {
                if (sum + arr[j] >= S) {
                    ans = Math.min(ans, j - i + 1);
                }
            }
//            System.out.println("i: " + i + " j: " + j + " sum: " + sum + " ans: " + ans);
            sum -= arr[i];
        }
        if (ans == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(ans);
        }
    }
}
/*
10 15
5 1 3 5 10 7 4 9 2 8

10 10
1 1 1 1 1 1 1 1 1 1

5 10
1 1 1 8 10

10 11
3 1 1 1 1 1 1 1 1 2

10 10
10 1 1 1 1 1 1 1 1 1
 */
