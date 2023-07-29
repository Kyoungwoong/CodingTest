package Baekjoon.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1654 {
    private static int N, K;
    private static int[] arr;

    public static long canInstall(long target) {
        long sum = 0;
        for (int i = 0; i < K; i++) {
            sum += (arr[i] / target);
        }
        return sum;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[K];
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        long low = 0;
        long high = arr[K-1];
        long ans = -1;

        while (low <= high) {
            long mid = (low + high) / 2;

            if (canInstall(mid) < N) {
                high = mid - 1;
            }else{
                ans = Math.max(ans, mid);
                low = mid + 1;
            }
        }
        System.out.println(ans);

    }
}
