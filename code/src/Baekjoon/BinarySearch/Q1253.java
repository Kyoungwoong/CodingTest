package Baekjoon.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1253 {
    private static int N;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (isGood(arr[i], i)) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    private static boolean isGood(int target, int idx) {
        int low = 0, high = N-1;

        while (low < high) {
            if (high == idx) {
                high--;
                continue;
            } else if (low == idx) {
                low++;
                continue;
            }

            int sum = arr[low] + arr[high];

            if (sum == target) {
//                System.out.println("target: " + target +
//                        " low: " + arr[low] + " high: " + arr[high]);
                return true;
            }

            if (sum < target) {
                low++;
            } else {
                high--;
            }
        }

        return false;
    }
}

/*
https://www.acmicpc.net/board/view/157466
 */