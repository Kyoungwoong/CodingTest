package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q4123 {
    private static int tc, N, max, min, ans;
    private static int[] operator;
    private static List<Integer> operand = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= tc; t++) {
            N = Integer.parseInt(br.readLine());

            operator = new int[4]; // 0: +      1: -      2: *        3: /
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                operator[i] = Integer.parseInt(st.nextToken());
//                System.out.print(operator[i] + " ");
            }
//            System.out.println();

            operand = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                operand.add(Integer.parseInt(st.nextToken()));
//                System.out.print(operand.get(i) + " ");
            }
//            System.out.println();

            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            dfs(1, operand.get(0));

            ans = max - min;
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }
    private static void dfs(int pos, int num) {
        if (pos == N) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }

        if (operator[0] > 0) {
            operator[0]--;
            dfs(pos + 1, num + operand.get(pos));
            operator[0]++;
        }

        if (operator[1] > 0) {
            operator[1]--;
            dfs(pos + 1, num - operand.get(pos));
            operator[1]++;
        }

        if (operator[2] > 0) {
            operator[2]--;
            dfs(pos + 1, num * operand.get(pos));
            operator[2]++;
        }

        if (operator[3] > 0) {
            operator[3]--;
            dfs(pos + 1, num / operand.get(pos));
            operator[3]++;
        }
    }
}
/*
10
5
2 1 0 1
3 5 3 7 9
6
4 1 0 0
1 2 3 4 5 6
5
1 1 1 1
9 9 9 9 9
6
1 4 0 0
1 2 3 4 5 6
4
0 2 1 0
1 9 8 6
6
2 1 1 1
7 4 4 1 9 3
7
1 4 1 0
2 1 6 7 6 5 8
8
1 1 3 2
9 2 5 3 4 9 5 6
10
1 1 5 2
8 5 6 8 9 2 6 4 3 2
12
2 1 6 2
2 3 7 9 4 5 1 9 2 5 6 4
 */