package Baekjoon.Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q24060 {
    private static int[] arr, sorted;
    private static int N, K, ans = 0, result = -1;
    public static void merge(int s, int e) {
        if (s < e) {
            int mid = (s + e) / 2;
            merge(s, mid);
            merge(mid + 1, e);
            mergeSort(s, e, mid);
        }
    }

    public static void mergeSort(int s, int e, int mid) {
        int left = s, right = mid + 1, k = s;
        while (left <= mid && right <= e) {
            if (arr[left] < arr[right]) {
                sorted[k++] = arr[left++];
            } else {
                sorted[k++] = arr[right++];
            }
        }

        if (left <= mid) {
            while (left <= mid) {
                sorted[k++] = arr[left++];
            }
        } else {
            while (right <= e) {
                sorted[k++] = arr[right++];
            }
        }

        for (int i = s; i <= e; i++) {
            ans++;
            arr[i] = sorted[i];
            if (ans == K) {
                result = arr[i];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        sorted = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
//            System.out.print(arr[i] + " ");
        }
        merge(0, N-1);
        System.out.println(result);
    }
}
