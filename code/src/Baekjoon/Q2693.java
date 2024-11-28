package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Q2693 {
    // https://www.acmicpc.net/problem/2693
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        List<Integer> A;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            A = new ArrayList<Integer>();
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                A.add(Integer.parseInt(st.nextToken()));
            }
            Collections.sort(A, Collections.reverseOrder());
            sb.append(A.get(2) + "\n");
        }
        System.out.println(sb.toString());
    }
}
