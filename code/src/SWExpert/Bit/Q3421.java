package SWExpert.Bit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q3421 {
    private static int tc, N, M;
    private static boolean[][] disabled;

    public static void main(String[] args) throws IOException {
//        prev();
        now();
    }
    private static void now() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            long[][] conflict = new long[M][2];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                long a = 1 << (Integer.parseInt(st.nextToken()) - 1);
                long b = 1 << (Integer.parseInt(st.nextToken()) - 1);
                conflict[i][0] = a;
                conflict[i][1] = b;
            }

            long count = 0;
            for (int i = 0; i < (1 << N); i++) {
                boolean check = false;
                for (int j = 0; j < M; j++) {
                    long foodA = conflict[j][0];
                    long foodB = conflict[j][1];
                    if ((i & foodA) != 0 && (i & foodB) != 0) {
                        check = true;
                        break;
                    }
                }
                if (!check) {
                    count++;
                }
            }
            sb.append("#").append(t).append(" ").append(count).append("\n");
        }
        System.out.println(sb);
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] conflict = new int[N]; // conflict[i] : i와 함께 못하는 멤버들 비트마스크

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1; // 0-index
                int b = Integer.parseInt(st.nextToken()) - 1;
                conflict[a] |= (1 << b);
                conflict[b] |= (1 << a);
            }

            int count = 0;

            for (int mask = 0; mask < (1 << N); mask++) {
                boolean valid = true;
                for (int i = 0; i < N; i++) {
                    if ((mask & (1 << i)) != 0) {
                        // i가 포함되어 있고, i와 함께 있으면 안 되는 사람도 mask에 포함되어 있다면 invalid
                        if ((mask & conflict[i]) != 0) {
                            valid = false;
                            break;
                        }
                    }
                }
                if (valid) count++;
            }

            sb.append("#").append(t).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }

//    private static void dfs(int pos, int limit, List<Integer> list) {
//        if (pos == limit) {
//            StringBuilder sb = new StringBuilder();
//            for (int num : list) {
//                sb.append(num);
//            }
//            duplicated.add(sb.toString());
//            return;
//        }
//
//        for (int i = pos+1; i <= 4; i++) {
//            list.add(i);
//            dfs(i, limit, list);
//            list.remove(list.size() - 1);
//        }
//    }
}
/*
3
3 2
1 2
2 3
3 0
3 3
1 2
1 3
2 3
 */
