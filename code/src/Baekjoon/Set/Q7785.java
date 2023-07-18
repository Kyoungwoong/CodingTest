package Baekjoon.Set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q7785 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        TreeSet<String> company = new TreeSet<>(Collections.reverseOrder());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String record = st.nextToken();
            if (record.equals("enter")) {
                company.add(name);
            } else {
                company.remove(name);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String name: company) {
            sb.append(name + "\n");
        }
        System.out.println(sb);
    }
}
