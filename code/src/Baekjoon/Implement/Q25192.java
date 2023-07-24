package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Q25192 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashSet<String> init = new HashSet<>();
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            if (name.equals("ENTER")) {
                init = new HashSet<>();
//                continue;
            } else {
                if (!init.contains(name)) {
                    init.add(name);
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}
