package ThisIsCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class SplitCity {
    static class End implements Comparable<End>{
        int a, b, distance;
        public End(int a, int b, int distance){
            this.a = a;
            this.b = b;
            this.distance = distance;
        }

        @Override
        public int compareTo(End n){
            return this.distance - n.distance;
        }

    }

    private static int N, M, result = 0;
    private static int[] parent;
    private static boolean[] visited;
    private static ArrayList<End> city = new ArrayList<>();

    public static int findParent(int n){
        if(parent[n] != n){
            parent[n] = findParent(parent[n]);
        }
        return parent[n];
    }

    public static void union(int a, int b){
        a = findParent(a);
        b = findParent(b);

        if(a < b){
            parent[b] = a;
        }else{
            parent[a] = b;
        }
    }

    public static void init(){
        for(int i = 0; i <= N; i++){
            parent[i] = i;
        }
    }

    public static void main(String[] args) throws IOException {
//        prev();

        oct16();
    }

    private static void oct16() throws IOException {
        // N개의 집에 M개의 길
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        visited = new boolean[N+1];

        init();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            city.add(new End(a, b, cost));
        }

        Collections.sort(city);

        int last = 0;
        for (End now : city) {
            int a = now.a;
            int b = now.b;
            int cost = now.distance;
            if(findParent(a) != findParent(b)){
                union(a, b);
                result += cost;
                last = cost;
            }
        }

        System.out.println("result = " + (result-last));
    }

    private static void prev() throws IOException {
        // N개의 집 M개의 길
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        visited = new boolean[N+1];

        init();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            city.add(new End(a, b, cost));
        }

        Collections.sort(city);
        int last = 0;

        for(int i = 0; i < city.size(); i++){
            End now = city.get(i);
            int a = now.a;
            int b = now.b;
            int cost = now.distance;
            if(findParent(a) != findParent(b)){
                union(a, b);
                result += cost;
                last = cost;
            }
        }

        System.out.println("result = " + (result-last));
    }
}
/*
7 12
1 2 3
1 3 2
3 2 1
2 5 2
3 4 4
7 3 6
5 1 5
1 6 2
6 4 1
6 5 3
4 5 3
6 7 4
 */
