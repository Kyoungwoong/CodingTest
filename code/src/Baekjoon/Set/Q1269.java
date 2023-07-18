package Baekjoon.Set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Q1269 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashSet<Integer> A = new HashSet<>();
        HashSet<Integer> B = new HashSet<>();

        int intersect = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int b = Integer.parseInt(st.nextToken());
            if (A.contains(b)) {
                intersect++;
            }
            B.add(b);
        }
        System.out.println(A.size() + B.size() - 2 * intersect);
    }
}
