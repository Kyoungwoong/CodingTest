package Baekjoon.PrefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q10986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];
        long[] cnt = new long[M];
        st = new StringTokenizer(br.readLine());
        long result = 0;
        for (int i = 1; i <= N; i++) {
            arr[i] = (Integer.parseInt(st.nextToken())) % M;
            arr[i] += arr[i - 1];
            arr[i] %= M;
            if (arr[i] == 0) {
                result++;
            }
            cnt[arr[i]]++;
        }

        for (int i = 0; i < M; i++) {
            if (cnt[i] > 1) {
                result += (cnt[i] * (cnt[i] - 1) / 2);
            }
        }
        System.out.println(result);

    }
}
