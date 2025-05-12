package Baekjoon.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Q12015 {
    static int[] memo;
    static int INF = Integer.MIN_VALUE;

    public static int binarySearch(int s, int e, int target) {
        int lo = 0;
        int hi = e;
        while (lo < hi) {
            int mid = (lo + hi) / 2;

            if(memo[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    public static void main(String[] args) throws IOException{
//        prev();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        memo = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memo[i] = Integer.parseInt(st.nextToken());
        }
        // dp로 풀면 N^2 따라서 이분탐색으로.

        int[] lis = new int[N];
        lis[0] = memo[0];
        int count = 1;
        for (int i = 1; i < N; i++) {
            int start = 0, end = count;
            int target = memo[i];

            if (lis[end - 1] < target) {
                lis[count++] = target;
            } else {
                int lo = 0;
                while (start <= end) {
                    int mid = (start + end) / 2;
                    if (lis[mid] >= target) {
                        end = mid - 1;
                        lo = mid;
                    } else {
                        start = mid + 1;
                    }
                }
                lis[lo] = target;
            }
        }
        System.out.println(count);
    }

//    private static void prev() throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int N = Integer.parseInt(br.readLine());
//        int[] seq = new int[N];
//        memo = new int[N];
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//        for (int i = 0; i < N; i++) {
//            seq[i] = Integer.parseInt(st.nextToken());
//        }
//
//        memo[0] = seq[0];
//        int lengthOfLIS = 1;
//
//        for (int i = 1; i < N; i++) {
//            int key = seq[i];
//
//            if (memo[lengthOfLIS - 1] < key) {
//                lengthOfLIS++;
//                memo[lengthOfLIS - 1] = key;
//            }
//            else {
//                int lo = binarySearch(0, lengthOfLIS, key);
//                memo[lo] = key;
//            }
//
//        }
//        System.out.println(lengthOfLIS);
//    }
}
/*
5
30 40 50 20 40
 */
