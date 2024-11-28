package Baekjoon;

import java.io.*;
import java.util.*;

public class Q1292 {
    // https://www.acmicpc.net/problem/1292
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        // 수열 생성
        List<Integer> sequence = new ArrayList<>();
        for (int i = 1; sequence.size() <= B; i++) {
            for (int j = 0; j < i; j++) {
                sequence.add(i);
            }
        }

        // 구간 합 계산
        int ans = 0;
        for (int i = A - 1; i < B; i++) {
            ans += sequence.get(i);
        }

        System.out.println(ans);
    }
}
