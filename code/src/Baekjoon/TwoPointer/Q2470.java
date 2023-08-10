package Baekjoon.TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int left = 0, right = N - 1;
        int ans_l = 0, ans_r = 0;
        int ans = 2000000001;
        int temp, sum;
        while (left < right) {
            sum = arr[left] + arr[right];
//            System.out.println("left: " + left + " right: " + right + " sum: " + sum);
            temp = Math.abs(sum);
            if (temp < ans) {
                ans = temp;
                ans_l = left;
                ans_r = right;
            }
            if (sum > 0) {
                right--;
            }
            else {
                left++;
            }
        }

        System.out.println(arr[ans_l] + " " + arr[ans_r]);

    }
}
/*
5
-2 4 -99 -1 98
>> -99 98
9
-100 -90 0 90 130 130 130 130 130
>> -90 90
3
-3 1 3
>> -3 3

 */
