package ThisIsCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Plan {
    private static int N, M;
    private static ArrayList<Integer> graph;
    private static int[] arr;

    public static void init() {
        for (int i = 0; i < N; i++) {
            arr[i] = i;
        }
    }

    public static void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if(a < b){
            arr[b] = a;
        }else{
            arr[a] = b;
        }
    }

    public static int findParent(int n) {
        if (arr[n] != n) {
            arr[n] = findParent(arr[n]);
        }
        return arr[n];
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        graph = new ArrayList<>();

        init();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int edge = Integer.parseInt(st.nextToken());
                if (edge == 1) {
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            graph.add(Integer.parseInt(st.nextToken())-1);
        }

        for (int i = 0; i < M - 1; i++) {
            if (arr[graph.get(i)] != arr[graph.get(i + 1)]) {
                System.out.println("NO");
                System.exit(0);
            }
        }

        System.out.println("YES");

    }
}
/*
5 4
0 1 0 1 1
1 0 1 1 0
0 1 0 0 0
1 1 0 0 0
1 0 0 0 0
2 3 4 5
--- YES
6 4
0 1 0 1 0 0
1 0 1 1 0 0
0 1 0 0 0 0
1 1 0 0 0 0
0 0 0 0 0 1
0 0 0 0 1 0
2 3 4 5
 */
