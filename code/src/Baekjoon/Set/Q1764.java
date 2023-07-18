package Baekjoon.Set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Q1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashSet<String> list = new HashSet<>();
        TreeSet<String> ans = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            list.add(br.readLine());
        }
        for (int i = 0; i < M; i++) {
            String name = br.readLine();
            if (list.contains(name)) {
                ans.add(name);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(ans.size() + "\n");
        for (String name: ans) {
            sb.append(name + "\n");
        }

        System.out.println(sb);
    }
}
