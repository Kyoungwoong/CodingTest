package Baekjoon.Array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q10811 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] basket = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            basket[i] = i;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            while (a < b) {
                int temp = basket[a];
                basket[a++] = basket[b];
                basket[b--] = temp;
            }
        }
        for (int i = 1; i <= N; i++) {
            System.out.print(basket[i] + " ");
        }
    }
}
