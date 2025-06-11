package SWExpert.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q1247 {
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int testCase, N, ans = Integer.MAX_VALUE;
    private static Pair company, house;
    private static Pair[] customers;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= testCase; t++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            ans = Integer.MAX_VALUE;
            customers = new Pair[N];
            visited = new boolean[N];

            for (int i = 0; i < N + 2; i++) {
                if (i == 0) {
                    company = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                } else if (i == 1) {
                    house = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                } else {
                    customers[i - 2] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                }
            }

            dfs(new ArrayList<Integer>(), 0);

            sb.append("#").append(t).append(" ").append(ans);
            if (t != testCase) {
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void dfs(List<Integer> orders, int cnt) {
        if (cnt == N) {
            int sum = 0;
            sum = getSum(orders, sum);
            ans = Math.min(ans, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            orders.add(i);

            dfs(orders, cnt + 1);

            orders.remove(orders.size() - 1);
            visited[i] = false;
        }
    }

    private static int getSum(List<Integer> orders, int sum) {
        Pair cur = company;

        for (int nextIdx : orders) {
            Pair next = customers[nextIdx];
            sum += cal(cur, next);
            cur = next;
        }

        sum += Math.abs(cur.x - house.x) + Math.abs(cur.y - house.y);
        return sum;
    }

    private static int cal(Pair cur, Pair next) {
        return Math.abs(cur.x - next.x) + Math.abs(cur.y - next.y);
    }
}

/**
3
5
0 0 100 100 70 40 30 10 10 5 90 70 50 20
6
88 81 85 80 19 22 31 15 27 29 30 10 20 26 5 14
10
39 9 97 61 35 93 62 64 96 39 36 36 9 59 59 96 61 7 64 43 43 58 1 36
*/