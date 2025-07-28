package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q3459 {
    private static int testCase;
    private static Long N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t = 1; t <= testCase; t++) {
            N = Long.parseLong(br.readLine());

            sb.append(String.format("#%d %s\n", t, solve(N)));
        }

        System.out.println(sb);
    }

    public static String solve(long N) {
        long x = 1;
        int turn = 0; // 0: Alice, 1: Bob

        while (x <= N) {
            if (turn % 2 == 0) {
                // Alice's turn: try to go to 2x + 1
                x = 2 * x + 1;
            } else {
                // Bob's turn: go to 2x
                x = 2 * x;
            }
            turn++;
        }

        // 마지막으로 넘긴 사람은 패배 → 직전 사람이 승리
        return (turn % 2 == 0) ? "Bob" : "Alice";
    }
}
