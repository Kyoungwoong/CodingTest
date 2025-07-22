package SWExpert.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1265 {
    private static int tc, N, P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
//        for (int t = 1; t <= tc; t++) {
//            st = new StringTokenizer(br.readLine());
//            N = Integer.parseInt(st.nextToken());
//            P = Integer.parseInt(st.nextToken());
//            int[] num = new int[N / P + 1];
//            for (int i = 1; i <= N / P + 1; i++) {
//                if (N / P * i > N) {
//                    num[i - 1] = N % P;
//                } else {
//                    num[i - 1] = N / P;
//                }
//            }
//
////            for (int n : num) {
////                System.out.print(n);
////            }
////            System.out.println();
//
//            int size = num.length;
//            int idx = 0;
//            while ((num[size - 1] != N / P) && num[size - 1] > 0) {
//                num[idx++]++;
//                num[size - 1]--;
//            }
//
//            long sum = 1;
//            for (int i = 0; i < size; i++) {
//                if (i == size - 1) {
//                    if (num[i] == N / P) {
//                        sum *= num[i];
//                    }
//                } else {
//                    sum *= num[i];
//                }
//            }
//            sb.append("#").append(t).append(" ").append(sum).append("\n");
//        }
//        System.out.println(sb);
        for (int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());

            int q = N / P;
            int r = N % P;

            long result = 1;
            for (int i = 0; i < r; i++) {
                result *= (q + 1);
            }
            for (int i = r; i < P; i++) {
                result *= q;
            }

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }

        System.out.println(sb);
    }
}

/*
10
10 3
20 5
30 9
40 12
50 12
60 23
70 23
80 32
90 32
100 32
 */