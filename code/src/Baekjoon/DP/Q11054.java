package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11054 {
    private static int N;
    private static int[] arr, inc, dec, bytonic;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        inc = new int[N];
        dec = new int[N];
        bytonic = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            inc[i] = 1;
            dec[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    inc[i] = Math.max(inc[i], inc[j] + 1);
                }
                if (arr[j] > arr[i]) {
                    dec[i] = Math.max(dec[i], dec[j] + 1);
                }
            }
        }

        if (N == 1) {
            System.out.println(1);
            System.exit(0);
        }

        int[] temp = new int[N];
        temp[N-1] = 0;
        if (arr[N - 1] < arr[N - 2]) {
            temp[N-2] = 1;
        }
        for (int i = N-3; i >= 0; i--) {
            // i가 가장 클 때
            bytonic[i] = inc[i];

            for (int j = i + 1; j < N; j++) {
                if (arr[j] < arr[i]) {
                    temp[i] = Math.max(temp[i], temp[j] + 1);
                }
            }
            bytonic[i] = Math.max(Math.max(bytonic[i] + temp[i], dec[i]), inc[i]);
        }

//        for (int i = 0; i < N; i++) {
//            System.out.print(inc[i] + " ");
//        }
//        System.out.println();
//        for (int i = 0; i < N; i++) {
//            System.out.print(dec[i] + " ");
//        }
//        System.out.println();
//        for (int i = 0; i < N; i++) {
//            System.out.print(temp[i] + " ");
//        }
//        System.out.println();
//        for (int i = 0; i < N; i++) {
//            System.out.print(bytonic[i] + " ");
//        }
//        System.out.println();

        int ans = -1;
        for (int i = 0; i < N; i++) {
            ans = Math.max(bytonic[i], ans);
        }
        System.out.println(ans);

    }
}

