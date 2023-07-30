package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] seq = new int[N];
        memo = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        memo[0] = seq[0];
        int lengthOfLIS = 1;

        for (int i = 1; i < N; i++) {
            int key = seq[i];

            if (memo[lengthOfLIS - 1] < key) {
                lengthOfLIS++;
                memo[lengthOfLIS - 1] = key;
            }
            else {
                int lo = binarySearch(0, lengthOfLIS, key);
                memo[lo] = key;
            }

        }
        System.out.println(lengthOfLIS);
    }
}
/*
5
30 40 50 20 40
 */
