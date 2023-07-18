package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q25206 {
    enum received{
        Ap,
        A0,
        Bp,
        B0,
        Cp,
        C0,
        Dp,
        D0,
        F
    }
    public static double[] degree = {4.5, 4.0, 3.5, 3.0, 2.5, 2.0, 1.5, 1.0, 0.0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double sum = 0;
        int degrees = 0;

        for (int i = 0; i < 20; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String classes = st.nextToken();
            double pivot = Double.parseDouble(st.nextToken());
            String get = st.nextToken();
            switch (get) {
                case "A+":
                    sum += pivot * degree[received.Ap.ordinal()];
                    break;
                case "A0":
                    sum += pivot * degree[received.A0.ordinal()];
                    break;
                case "B+":
                    sum += pivot * degree[received.Bp.ordinal()];
                    break;
                case "B0":
                    sum += pivot * degree[received.B0.ordinal()];
                    break;
                case "C+":
                    sum += pivot * degree[received.Cp.ordinal()];
                    break;
                case "C0":
                    sum += pivot * degree[received.C0.ordinal()];
                    break;
                case "D+":
                    sum += pivot * degree[received.Dp.ordinal()];
                    break;
                case "D0":
                    sum += pivot * degree[received.D0.ordinal()];
                    break;
                case "F":
                    sum += pivot * degree[received.F.ordinal()];
                    break;
                case "P":
                    break;
            }
            if(!get.equals("P")) {
                degrees += (int)pivot;
            }
//            System.out.println("sum: " + sum + " degrees = " + degrees);
        }
        System.out.printf("%.6f", sum / degrees);

    }
}
