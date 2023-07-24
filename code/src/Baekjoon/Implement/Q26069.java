package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Q26069 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashSet<String> dance = new HashSet<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String A = st.nextToken();
            String B = st.nextToken();
            if (A.equals("ChongChong") || B.equals("ChongChong")) {
                dance.add(A);
                dance.add(B);
            } else if (dance.contains(A)) {
                dance.add(B);
            } else if (dance.contains(B)) {
                dance.add(A);
            }
        }
//        for (String name: dance) {
//            System.out.println(name);
//        }
        System.out.println(dance.size());
    }
}
