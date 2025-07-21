package SWExpert.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q5521 {
    private static int tc, N, M;
    private static List<List<Integer>> friends;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            friends = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                friends.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                friends.get(a).add(b);
                friends.get(b).add(a);
            }

            int sum = 0;
            boolean[] already = new boolean[N + 1];
            already[1] = true;
            for (int num : friends.get(1)) {
                if (!already[num]) {
//                    System.out.print(num + " ");
                    sum++;
                    already[num] = true;
                }
                for (int next : friends.get(num)) {
                    if (!already[next]) {
//                        System.out.println(next + " ");
                        sum++;
                        already[next] = true;
                    }
                }
//                System.out.println();
            }

            sb.append("#").append(t).append(" ").append(sum).append("\n");
        }
        System.out.println(sb);
    }
}
/*
2
6 5
2 3
3 4
4 5
5 6
2 5
6 5
1 2
1 3
3 4
2 3
4 5
 */