package Baekjoon.DivideConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q1629 {
    private static int A, B, MOD;
    private static ArrayList<Integer> result = new ArrayList<>();

    public static long find(int size) {
        int half = size / 2;
        if (size == 0) {
            return 1;
        } else if (size == 1) {
            return A % MOD;
        }

        long temp = find(half);
        if (size % 2 == 0) {
            return temp * temp % MOD;
        } else {
            return (temp * temp % MOD) * A % MOD;
        }
//        return (find(half) % MOD * find(size - half) % MOD) % MOD;
    }

    public static void init() {
        result.add(1);
        result.add(A % MOD);
        for (int i = 2; i <= B; i++) {
            int a = result.get(i / 2);
            int b = result.get(i - (i/2));
//            System.out.println(i + " "+ a + " "+b+ " ");
            result.add(((a % MOD) * (b % MOD)) % MOD);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        MOD = Integer.parseInt(st.nextToken());
//        init();
//        System.out.println(result.get(B));
        System.out.println(find(B));


    }
}
