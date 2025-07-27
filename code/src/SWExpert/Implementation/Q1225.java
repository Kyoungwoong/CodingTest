package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1225 {
    private static int tc = 10, N = 8, round = 5;
    private static int[] arr;
    private static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int t = 1; t <= tc; t++) {
            Integer.parseInt(br.readLine());
            arr = new int[N];
            q = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                q.add(Integer.parseInt(st.nextToken()));
            }

            int idx = 1;
            while (true) {
                int num = q.poll();
                num -= idx++;
                if (num <= 0) {
                    q.add(0);
                    break;
                } else {
                    q.add(num);
                }

                if (idx > 5) idx = 1;
            }

            sb.append("#").append(t).append(" ");
            while (!q.isEmpty()) {
                sb.append(q.poll()).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}

/*
1
9550 9556 9550 9553 9558 9551 9551 9551
2
2419 2418 2423 2415 2422 2419 2420 2415
3
7834 7840 7840 7835 7841 7835 7835 7838
4
4088 4087 4090 4089 4093 4085 4090 4084
5
2945 2946 2950 2948 2942 2943 2948 2947
6
670 667 669 671 670 670 668 671
7
8869 8869 8873 8875 8870 8872 8871 8873
8
1709 1707 1712 1712 1714 1710 1706 1712
9
10239 10248 10242 10240 10242 10242 10245 10235
10
6580 6579 6574 6580 6583 6580 6577 6581

 */