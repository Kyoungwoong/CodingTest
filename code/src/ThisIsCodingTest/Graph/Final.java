package ThisIsCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Final {
    private static int T, N, M;
    // 작년 순위
    private static int[] previous;
    private static int[] in;
    private static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            N = Integer.parseInt(br.readLine());
            previous = new int[N+1];
            list = new ArrayList[N+1];
            in = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                previous[i] = Integer.parseInt(st.nextToken());
                list[i] = new ArrayList<>();
            }

            for (int i = 1; i <= N; i++) {
                int top = previous[i];
                for (int j = i+1; j <= N; j++) {
                    int bottom = previous[j];
                    list[top].add(bottom);
                    in[bottom]++;
                }
            }

            M = Integer.parseInt(br.readLine());

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (list[a].contains(b)) {
                    list[a].remove((Integer) b);
                    list[b].add(a);
                    in[b]--;
                    in[a]++;
                } else {
                    list[b].remove((Integer) a);
                    list[a].add(b);
                    in[a]--;
                    in[b]++;
                }
            }

            int cnt = 0;
            Queue<Integer> q = new LinkedList<>();

            for (int i = 1; i <= N; i++) {
                if (in[i] == 0) {
                    q.offer(i);
                    cnt++;
                }
            }

            if (cnt > 1) {
                System.out.println("?");
                continue;
            }

            boolean check = false;
            StringBuilder sb = new StringBuilder();

            for (int i = 1; i <= N; i++) {
                if (q.isEmpty()) {
                    System.out.println("IMPOSSIBLE");
                    check = true;
                    break;
                }

                int prev = q.poll();
                sb.append(prev + " ");
                for (int post : list[prev]) {
                    in[post]--;
                    if (in[post] == 0) {
                        q.offer(post);
                    }
                }
            }
            if(check) continue;

            System.out.println(sb);


        }
    }
}
/*
3
5
5 4 3 2 1
2
2 4
3 4
3
2 3 1
0
4
1 2 3 4
3
1 2
3 4
2 3
---
5 3 2 4 1
2 3 1
IMPOSSIBLE
 */
