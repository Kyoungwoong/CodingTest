package Baekjoon.Set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Q1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashMap<String, Integer> strMap = new HashMap<>();
        HashMap<Integer, String> intMap = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            strMap.put(name, i);
            intMap.put(i, name);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String find = br.readLine();
            if (find.charAt(0) >= '0' && find.charAt(0) <= '9') {
                sb.append(intMap.get(Integer.parseInt(find)) + "\n");
            } else {
                sb.append(strMap.get(find) + "\n");
            }
        }
        System.out.println(sb);
    }
}
