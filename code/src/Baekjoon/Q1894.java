package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1894 {
    static class Pair {
        double x, y;

        public Pair(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) break;

            st = new StringTokenizer(line);
            // 한 줄에 8개의 실수
            Pair p1 = new Pair(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
            Pair p2 = new Pair(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
            Pair p3 = new Pair(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
            Pair p4 = new Pair(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));

            Pair A = null, B = null, C = null;

            // 공통 꼭짓점(중복 좌표) 찾기
            if (same(p1, p3)) { A = p1; B = p2; C = p4; }
            else if (same(p1, p4)) { A = p1; B = p2; C = p3; }
            else if (same(p2, p3)) { A = p2; B = p1; C = p4; }
            else if (same(p2, p4)) { A = p2; B = p1; C = p3; }

            // D = B + C - A
            double dx = B.x + C.x - A.x;
            double dy = B.y + C.y - A.y;

            sb.append(String.format("%.3f %.3f%n", dx, dy));
        }

        System.out.print(sb);
    }

    private static boolean same(Pair p1, Pair p2) {
        if (p1.x == p2.x && p1.y == p2.y) {
            return true;
        }
        return false;
    }
}
