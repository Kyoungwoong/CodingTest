package Baekjoon.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q15650 {
    private static int N, M;
    private static ArrayList<Integer> arr = new ArrayList<>();
    public static StringBuilder sb = new StringBuilder();

    public static void print() {
        for (int i = 0; i < arr.size(); i++) {
            sb.append(arr.get(i) + " ");
        }
        sb.append("\n");
    }

    public static void count(int num, int cnt) {
        if (cnt == M + 1) {
            if (arr.size() == M) {
                print();
            }
            return;
        }

        for (int i = num; i <= N; i++) {
            arr.add(i);
            count(i + 1, cnt + 1);
            arr.remove(arr.size() - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        count(1, 1);
        System.out.println(sb);
    }
}
