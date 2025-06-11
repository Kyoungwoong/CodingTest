package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1209 {
    private static int tc = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int t = 1; t <= tc; t++) {
            br.readLine();
            int[] sumRow = new int[100];
            int[] sumCol = new int[100];
            int[] sumX = new int[2];

            for (int i = 0; i < 100; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    sumRow[i] += num;
                    sumCol[j] += num;
                    if (i == j) {
                        sumX[0] += num;
                    } else if (i + j == 99) {
                        sumX[1] += num;
                    }
                }
            }

            int max = 0;
            for (int i = 0; i < 100; i++) {
                max = Math.max(max, sumRow[i]);
                max = Math.max(max, sumCol[i]);
            }
            max = Math.max(max, Math.max(sumX[0], sumX[1]));
            sb.append("#").append(t).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }
}
