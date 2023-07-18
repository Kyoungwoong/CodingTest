package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Q1316 {
    private static int N;
    private static HashSet<Character> checker;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        char last = '0';
        int ans = 0;
        for (int i = 0; i < N; i++) {
            boolean check = true;
            checker = new HashSet<>();
            String cmd = br.readLine();
            last = cmd.charAt(0);
            checker.add(cmd.charAt(0));
            for (int j = 1; j < cmd.length(); j++) {
                char put = cmd.charAt(j);
                if (put != last && checker.contains(put)) {
                    check = false;
                    break;
                } else {
                    if(!checker.contains(put)){
                        checker.add(put);
                        last = put;
                    }
                }
            }
            if (check) {
                ans++;
            }
        }
        System.out.println(ans);
    }
}
