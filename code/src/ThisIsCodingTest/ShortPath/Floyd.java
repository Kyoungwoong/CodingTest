package ThisIsCodingTest.ShortPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Path{
    int reach, distance;

    public Path(int reach, int distance) {
        this.reach = reach;
        this.distance = distance;
    }
}

public class Floyd {
    private static int n, m, a, b, c, MAX_VALUE = 100000;
    private static ArrayList<ArrayList<Path>> graph = new ArrayList<>();
    private static int[][] d;

    public static void init() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i == j) {
                    d[i][j] = 0;
                }else{
                    d[i][j] = MAX_VALUE;
                }
            }
        }
    }

    public static void Floyd() {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        d = new int[n][n];

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        init();

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken());
//            graph.get(a).add(new Path(b, c));

            d[a][b] = Math.min(d[a][b], c);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(d[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        Floyd();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(d[i][j] + " ");
            }
            System.out.println();
        }
    }
}
/*
5
14
1 2 2
1 3 3
1 4 1
1 5 10
2 4 2
3 4 1
3 5 1
4 5 3
3 5 10
3 1 8
1 4 2
5 1 7
3 4 2
5 2 4
---
0 2 3 1 4
12 0 15 2 5
8 5 0 1 1
10 7 13 0 3
7 4 10 6 0
 */
