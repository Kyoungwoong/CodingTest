package ssafy.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q4008 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int testCase, N, max, min, ans;
    private static int[] operator;
    private static List<Integer> operand = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine());

        for (int test = 1; test <= testCase; test++) {
            input();

            solve(1, operand.get(0));

            ans = max - min;
            sb.append(String.format("#%d %d\n", test, ans));
        }
        System.out.println(sb);
    }
    private static void solve(int pos, int num) {
        if (pos == N) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }

        if (operator[0] > 0) {
            operator[0]--;
            solve(pos + 1, num + operand.get(pos));
            operator[0]++;
        }

        if (operator[1] > 0) {
            operator[1]--;
            solve(pos + 1, num - operand.get(pos));
            operator[1]++;
        }

        if (operator[2] > 0) {
            operator[2]--;
            solve(pos + 1, num * operand.get(pos));
            operator[2]++;
        }

        if (operator[3] > 0) {
            operator[3]--;
            solve(pos + 1, num / operand.get(pos));
            operator[3]++;
        }
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        operator = new int[4]; // 0: +      1: -      2: *        3: /
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }

        operand = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            operand.add(Integer.parseInt(st.nextToken()));
        }

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
    }
}
