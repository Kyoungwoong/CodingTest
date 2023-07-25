package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q12015 {
    static int[] memo;
    static int INF = Integer.MIN_VALUE;

    public static int binarySearch(int s, int e, int target) {
        while (s < e) {
            int mid = (s + e) / 2;
            if (memo[mid] > target) {
                s = mid + 1;
            } else {
                e = mid;
            }
        }
        return s;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st= new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        memo = new int[n+1];
        int memo_idx = 0;
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > memo[memo_idx]) {
                memo_idx++;
                memo[memo_idx] = arr[i];
            } else {
                idx = binarySearch(0, memo_idx, arr[i]);
                memo[idx] = arr[i];
            }
        }

        System.out.println(memo_idx);
    }
}
