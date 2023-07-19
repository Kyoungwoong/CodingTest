package Baekjoon.Array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q10807 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(br.readLine()) + 100;
        int[] arr = new int[201];
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken()) + 100;
            arr[num]++;
        }
        System.out.println(arr[v]);
    }
}
