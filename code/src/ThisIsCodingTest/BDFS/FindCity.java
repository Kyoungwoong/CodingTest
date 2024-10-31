package ThisIsCodingTest.BDFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FindCity {
    static class City{
        int x, distance;
        public City(int x, int distance){
            this.x = x;
            this.distance = distance;
        }
    }

    public static int N, M, K, X;
    public static int[] visited;
    public static ArrayList<Integer>[] graph;
    private static List<Integer> ans = new ArrayList<>();
    private static List<List<Integer>> roads = new ArrayList<>();

    public static void main(String[] args) throws IOException {
//        prev();
        oct30();
    }

    private static void oct30() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            roads.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            roads.get(Integer.parseInt(st.nextToken())).add(Integer.parseInt(st.nextToken()));
        }

        bfs(X);

        if (ans.size() == 0) {
            System.out.println("-1 = " + -1);
        }
        Collections.sort(ans);
        for (int desc : ans) {
            System.out.print(desc + "\t");
        }
    }

    private static void bfs(int start) {
        Queue<City> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        visited[start] = true;

        for (int desc : roads.get(start)) {
            q.add(new City(desc, 1));
            visited[desc] = true;
        }

        while (!q.isEmpty()) {
            City now = q.poll();
//            System.out.println("now.x = " + now.x + "\t" + now.distance);
            for (int desc : roads.get(now.x)) {
                if (!visited[desc]) {
                    System.out.println("desc = " + desc);
                    visited[desc] = true;
                    q.add(new City(desc, now.distance + 1));
                }
            }
            if (now.distance == K) {
                ans.add(now.x);
            }
        }
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken())-1;

        graph = new ArrayList[N];
        visited = new int[N];

        for(int i = 0 ; i < N; i++){
            graph[i] = new ArrayList<>();
            visited[i] = -1;
        }


        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            graph[s].add(e);
//            graph[e].add(s);
        }

        Queue<Integer> q = new LinkedList<>();
        visited[X] = 0;
        for(int i = 0; i < graph[X].size(); i++){
            q.add(graph[X].get(i));
            visited[graph[X].get(i)] = visited[X]+1;
        }

        while(!q.isEmpty()){
            int cur = q.poll();
            for(int i = 0; i < graph[cur].size(); i++){
                if(visited[graph[cur].get(i)] == -1) {
                    q.add(graph[cur].get(i));
                    visited[graph[cur].get(i)] = visited[cur] + 1;
                }
            }
        }

        boolean check = false;
        for(int i = 0; i < N; i++){
            if(visited[i] == K){
                System.out.println(i+1);
                check = true;
            }
        }
        if(!check) System.out.println(-1);
    }
}
/*
4 4 2 1
1 2
1 3
2 3
2 4
--- 4

4 3 2 1
1 2
1 3
1 4
--- -1

4 4 1 1
1 2
1 3
2 3
2 4
--- 2 3
 */
