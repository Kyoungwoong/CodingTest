package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q1256 {
    private static int tc, K;
    private static List<String> postfix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= tc; t++) {
            K = Integer.parseInt(br.readLine());
            String line = br.readLine();

            postfix = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                postfix.add(line.substring(i));
            }

            Collections.sort(postfix);

            sb.append("#").append(t).append(" ")
                    .append(postfix.size() >= K ? postfix.get(K-1) : "none").append("\n");
        }
        System.out.println(sb);
    }
}
