package Baekjoon.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q10816 {
    private static int[] arr;

    // target보다 큰 최초의 인덱스
    public static int upperBound(int target) {
        int lo = 0;
        int ri = arr.length;
        while (lo < ri) {
            int mid = (lo + ri) / 2;
            if (arr[mid] <= target) {
                lo = mid + 1;
            } else {
                ri = mid;
            }
        }
        return ri;
    }

    // target이 나타나는 최초의 위치
    public static int lowerBound(int target) {
        int lo = 0;
        int ri = arr.length;
        while (lo < ri) {
            int mid = (lo + ri) / 2;
            if (arr[mid] < target) {
                lo = mid + 1;
            } else {
                ri = mid;
            }
        }
        return ri;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            sb.append(upperBound(target) - lowerBound(target));
            sb.append(" ");
        }
        System.out.println(sb);
    }
}
