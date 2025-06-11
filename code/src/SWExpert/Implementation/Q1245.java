package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1245 {
    private static int testCase, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= testCase; t++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            double[] xs = new double[N];
            int[] gs = new int[N];
            for (int i = 0; i < N; i++) {
                xs[i] = Double.parseDouble(st.nextToken());
            }

            for (int i = 0; i < N; i++) {
                gs[i] = Integer.parseInt(st.nextToken());
            }

            sb.append("#").append(t).append(" ");
            for (int i = 1; i < N; i++) {
                double mid = binarySearch(xs, gs, i);
                sb.append(String.format("%.10f", mid)).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static double binarySearch(double[] xs, int[] gs, int limit) {
        double left = xs[limit - 1];
        double right = xs[limit];
        double mid = 0;

        for (int cnt = 0; cnt < 100; cnt++) {
            mid = (left + right) / 2.0;
            double forceLeft = 0;
            double forceRight = 0;

            // 왼쪽 자성체들
            for (int i = 0; i < limit; i++) {
                double d = mid - xs[i];
                forceLeft += gs[i] / (d * d);
            }

            // 오른쪽 자성체들
            for (int i = limit; i < N; i++) {
                double d = xs[i] - mid;
                forceRight += gs[i] / (d * d);
            }

            if (forceLeft < forceRight) {
                right = mid;
            } else if (forceLeft > forceRight) {
                left = mid;
            } else {
                return mid;
            }
        }

        return mid;
    }
}
/*
2
2
1 2 1 1
2
1 2 1 1000
 */
