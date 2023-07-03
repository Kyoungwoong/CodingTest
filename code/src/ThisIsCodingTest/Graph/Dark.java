package ThisIsCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Path implements Comparable<Path>{
    int src, dest, dist;
    public Path(int src, int dest, int dist) {
        this.src = src;
        this.dest = dest;
        this.dist = dist;
    }

    @Override
    public int compareTo(Path path) {
        if (this.dist == path.dist) {
            return this.src - path.src;
        }
        return this.dist - path.dist;
    }
}

public class Dark {
    private static int N, M, MAX = 0, min = 0, ans = 0;
    private static ArrayList<Path> graph = new ArrayList<>();
    private static int[] arr;

    public static int findParent(int n) {
        if (arr[n] != n) {
            arr[n] = findParent(arr[n]);
        }
        return arr[n];
    }

    public static void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (a < b) {
            arr[b] = a;
        }else{
            arr[a] = b;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        for (int i = 0; i <= N; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            graph.add(new Path(x, y, z));
            MAX += z;
        }

        Collections.sort(graph);

        int min = 0;
        for (int i = 0; i < graph.size(); i++) {
            int src = graph.get(i).src;
            int dest = graph.get(i).dest;
            int dist = graph.get(i).dist;

            if (findParent(src) != findParent(dest)) {
                union(src, dest);
                min += dist;
            }
        }

        for (int i = 0; i <= N; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println(MAX);
        System.out.println(min);
    }
}
/*
7 11
0 1 7
0 3 5
1 2 8
1 3 9
1 4 7
2 4 5
3 4 15
3 5 6
4 5 8
4 6 9
5 6 11
--- 51
 */
