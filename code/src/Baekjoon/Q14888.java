package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14888 {
    // https://www.acmicpc.net/problem/14888
    private static int[] A;
    private static int[] op = new int[4]; // + - * /
    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;
    private static int maxCnt = 0;

    private static void find(int num, int std, int idx) {
        if (std == maxCnt) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (op[i] == 0) continue;
            op[i]--;
            switch (i) {
                case 0:
                    find(num + A[idx], std + 1, idx + 1);
                    break;
                case 1:
                    find(num - A[idx], std + 1, idx + 1);
                    break;
                case 2:
                    find(num * A[idx], std + 1, idx + 1);
                    break;
                case 3:
                    find(num / A[idx], std + 1, idx + 1);
                    break;
            }
            op[i]++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        A = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
            maxCnt += op[i];
        }

        find(A[0], 0, 1);

        System.out.println(max);
        System.out.println(min);
    }
}
