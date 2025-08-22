package ssafy.implementation;

import java.io.*;
import java.util.*;

public class Q5215_3 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static int[] taste, cal;

    private static int testCase, N, L;

    public static void main(String[] args) throws Exception {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int test = 1; test <= testCase; test++) {
            input();
            int best = solve();
            sb.append(String.format("#%d %d\n", test, best));
        }

        System.out.print(sb.toString());
    }

    private static int solve() {
        int result = 0;

        for (int k = 0; k <= N; k++) {
            if (k == 0) {
                result = Math.max(result, 0);
                continue;
            }

            int[] pick = new int[N];
            for (int i = N - k; i < N; i++) {
                pick[i] = 1;
            }

            do {
                int sumCal = 0;
                int sumTaste = 0;

                for (int i = 0; i < N; i++) {
                    if (pick[i] == 1) {
                        sumCal += cal[i];
                        if (sumCal > L) {
                            break;
                        }
                        sumTaste += taste[i];
                    }
                }

                if (sumCal <= L) {
                    result = Math.max(result, sumTaste);
                }
            } while (nextPermutation(pick));
        }

        return result;
    }

    private static boolean nextPermutation(int[] a) {
        int n = a.length;

        int i = n - 1;
        while (i > 0 && a[i - 1] >= a[i]) {
            i--;
        }
        if (i == 0) {
            return false;
        }

        int j = n - 1;
        while (a[j] <= a[i - 1]) {
            j--;
        }

        swap(a, i - 1, j);
        reverse(a, i, n - 1);
        return true;
    }

    private static void swap(int[] a, int x, int y) {
        int t = a[x];
        a[x] = a[y];
        a[y] = t;
    }

    private static void reverse(int[] a, int l, int r) {
        while (l < r) {
            swap(a, l, r);
            l++;
            r--;
        }
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        taste = new int[N];
        cal = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            taste[i] = Integer.parseInt(st.nextToken());
            cal[i] = Integer.parseInt(st.nextToken());
        }
    }
}

/*
1
5 1000
100 200
300 500
250 300
500 1000
400 400
 */