package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q1038 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N < 10) {
            System.out.println(N);
            return;
        }

        Queue<Long> decreaseNum = new LinkedList<>();
        for (int i = 0; i <= 9; i++) {
            decreaseNum.add(Long.valueOf(i));
        }

        int result = 9;
        long ans = -1;
        while (!decreaseNum.isEmpty()) {
            long cur = decreaseNum.poll();
            long lastDigit = cur % 10;

            for (int i = 0; i < lastDigit; i++) {
                long next = cur * 10 + i;
                decreaseNum.add(next);
                result++;
                if (result == N) {
                    ans = next;
                    break;
                }
            }
        }

        System.out.println(ans);
    }
}
