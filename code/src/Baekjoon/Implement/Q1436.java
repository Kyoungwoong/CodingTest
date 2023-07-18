package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Q1436 {
    public static boolean isDevil(int num, int idx) {
        ArrayList<Integer> arr = new ArrayList<>();

        while (num > 0) {
            arr.add(num % 10);
            num /= 10;
        }

        for (int i = 0; i <= arr.size()-3; i++) {
            if (arr.get(i) == arr.get(i + 1) && arr.get(i + 1) == arr.get(i + 2) && arr.get(i + 2) == 6) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int cnt = 0;
        int idx = 3;

        for (int i = 666; i <= 1000000000; i++) {
            if (i == 1000) {
                idx++;
            }
            if (isDevil(i, idx)) {
                cnt++;
            }
            if (cnt == N) {
                System.out.println(i);
                break;
            }
        }
    }
}
