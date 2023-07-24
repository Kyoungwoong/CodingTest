package Baekjoon.Number;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Q13909 {
//    public static boolean isPrime(int num) {
//        for (int i = 2; i * i <= num; i++) {
//            if (num % i == 0) {
//                return false;
//            }
//        }
//        return true;
//    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int ans = 0; // 1

        for (int i = 1; i*i <= N; i++) {
            ans++;
        }
        System.out.println(ans);
    }
}
