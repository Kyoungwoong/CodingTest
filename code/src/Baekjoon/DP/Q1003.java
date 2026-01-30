package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1003 {
    private static int T;
    private static Count[] countArray;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
//        countZero = new int[41];
//        countOne = new int[41];
        countArray = new Count[41];
        init();
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            sb.append(String.format("%d %d\n", countArray[n].countZero, countArray[n].countOne));
        }
        System.out.println(sb.toString());
    }

    private static void init() {
        countArray[0] = new Count(1, 0);
        countArray[1] = new Count(0, 1);

        for (int i = 2; i <= 40; i++) {
            Count a = countArray[i - 2];
            Count b = countArray[i - 1];
            countArray[i] = new Count(a.countZero + b.countZero, a.countOne + b.countOne);
        }
    }

    static class Count {
        int countZero = 0;
        int countOne = 0;

        public Count(int countZero, int countOne) {
            this.countZero = countZero;
            this.countOne = countOne;
        }
    }
}
