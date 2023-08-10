package Baekjoon.TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Q1644 {

    private static ArrayList<Integer> prime = new ArrayList<>();

    public static void isPrime() {
        boolean[] isPrime = new boolean[4000001];

        for (int i = 2; i <= 4000000; i++) {
            if (isPrime[i] == false) {
                prime.add(i);
            } else {
                continue;
            }
            for (int j = 2 * i; j <= 4000000; j += i) {
                isPrime[j] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        isPrime();

        int left = 0;
        int right = 0;
        int sum = 0;
        int size = prime.size();
        int cnt = 0;
        while (left <= right && right < size) {
            while(sum < N) {
                if (prime.get(right) > N) {
                    System.out.println(cnt);
                    System.exit(0);
                }
                sum += prime.get(right);
                right++;
                if (sum == N) {
                    cnt++;
                }
            }
            sum -= prime.get(left);
            left++;
            if (sum == N) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
