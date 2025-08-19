package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q10972 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();

    private static int N;
    private static int[] array;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.println(sb.toString());
    }

    private static void solve() {
        if (nextPermutation(array)) {
            for (int i = 0; i < N; i++) {
                if (i > 0) sb.append(' ');
                sb.append(array[i]);
            }
        } else {
            sb.append("-1");
        }
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
        int tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }

    private static void reverse(int[] a, int l, int r) {
        while (l < r) {
            swap(a, l++, r--);
        }
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        array = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
    }
}
