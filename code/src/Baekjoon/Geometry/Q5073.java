package Baekjoon.Geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q5073 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int[] tri;
        while (true) {
            st = new StringTokenizer(br.readLine());
            tri = new int[3];
            tri[0] = Integer.parseInt(st.nextToken());
            tri[1] = Integer.parseInt(st.nextToken());
            tri[2] = Integer.parseInt(st.nextToken());

            if (tri[0] == 0 && tri[1] == 0 && tri[2] == 0) {
                System.out.println(sb);
                break;
            }

            Arrays.sort(tri);

            if (tri[2] >= tri[0] + tri[1]) {
                sb.append("Invalid\n");
            } else {
                if (tri[0] == tri[1] && tri[1] == tri[2]) {
                    sb.append("Equilateral\n");
                } else if (tri[0] == tri[1] || tri[1] == tri[2] || tri[0] == tri[2]) {
                    sb.append("Isosceles\n");
                } else {
                    sb.append("Scalene\n");
                }
            }
        }
    }
}
