package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9095 {
    private static int T;
    private static int n;
    private static long[] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        array = new long[12];
        init();
        while (T-- > 0) {
            n = Integer.parseInt(br.readLine());
            sb.append(array[n]).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void init() {
        array[0] = 0;
        array[1] = 1;
        array[2] = 2;
        array[3] = 4;

        for (int i = 4; i <= 11; i++) {
            if (i - 3 >= 0) {
                array[i] = array[i - 3];
            }
            if (i - 2 >= 0) {
                array[i] += array[i - 2];
            }
            if (i - 1 >= 0) {
                array[i] += array[i - 1];
            }
        }

//        for (int i = 1; i <= 11; i++) {
//            System.out.print(array[i] + "\t");
//        }
//        System.out.println();
    }
}
