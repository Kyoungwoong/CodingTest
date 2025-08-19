package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Q1236 {
    static int N, M;

    static Set<Integer> missingRow = new HashSet<>();
    static Set<Integer> containCol = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int row = 0; row < N; row++) {
            String line = br.readLine();
            boolean flag = false;
            for (int col = 0; col < M; col++) {
                if (line.charAt(col) == 'X') {
                    flag = true;
                    containCol.add(col);
                }
            }
            if (!flag) {
                missingRow.add(row);
            }
        }

        int ans = -1;
        int missingColSize = M - containCol.size();
        if (missingColSize < missingRow.size()) {
            ans = missingRow.size();
        } else {
            ans = missingColSize;
        }

        System.out.println(ans);
    }
}
