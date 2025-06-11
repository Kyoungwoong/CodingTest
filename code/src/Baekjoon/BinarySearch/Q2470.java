package Baekjoon.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Q2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(list);

        int left = 0, right = N-1;
        int ans_l = 0, ans_r = 0;
        int ans = 2000000001;
        int temp, sum;
        while (left < right) {
            sum = list.get(left) + list.get(right);
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

        System.out.println(list.get(ans_l) + " " + list.get(ans_r));
    }
}
