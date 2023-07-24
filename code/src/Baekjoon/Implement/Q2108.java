package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2108 {
    public static int[] arr;
    public static int N;

    public static int avg() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
        }

        return (int)Math.round((double) sum / N);
    }

    public static int mid() {
        return arr[N / 2];
    }

    public static int frequent() {
        int[] fre = new int[8001];
        for (int i = 0; i < N; i++) {
            fre[arr[i] + 4000]++;
        }
        ArrayList<Integer> freq = new ArrayList<>();
        int ans = 0;
        for (int i = 0; i <= 8000; i++) {
            if (fre[i] == 0) {
                continue;
            }

            if (ans < fre[i]) {
                ans = fre[i];
                freq.removeAll(freq);
                freq.add(i - 4000);
            } else if (ans == fre[i]) {
                freq.add(i - 4000);
            }
        }

        if (freq.size() >= 2) {
            return freq.get(1);
        } else {
            return freq.get(0);
        }
    }

    public static int range() {
        return arr[N-1] - arr[0];
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        sb.append(avg() + "\n");
        sb.append(mid() + "\n");
        sb.append(frequent() + "\n");
        sb.append(range() + "\n");
        System.out.println(sb);
    }
}
