package Baekjoon.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1920 {
    private static long[] arr;

    public static int binarySearch(long key) {
        int lo = 0;
        int ri = arr.length - 1;
        while (lo <= ri) {
            int mid = (lo + ri) / 2;
            if (arr[mid] < key) {
                lo = mid + 1;
            } else if (arr[mid] > key) {
                ri = mid - 1;
            } else {
                return 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            long target = Long.parseLong(st.nextToken());
            sb.append(binarySearch(target) + "\n");
        }
        System.out.println(sb);
    }
}
