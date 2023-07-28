package Baekjoon.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q11501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[n];
            TreeMap<Integer, Integer> map = new TreeMap<>();
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[j] = num;
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            long sum = 0;
            int max = map.lastKey();
            for (int p = 0; p < n; p++) {
                if (arr[p] <= max) {
                    sum += (max - arr[p]);
                }

                if (map.get(arr[p]) == 1) {
                    map.remove(arr[p]);
                } else {
                    map.put(arr[p], map.getOrDefault(arr[p], 0) - 1);
                }

                if (arr[p] == max && p != n - 1) {
                    max = map.lastKey();
                }
            }
            sb.append(sum + "\n");
        }
        System.out.println(sb);
    }
}
