package ThisIsCodingTest.BDFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class City{
    int x, distance;
    public City(int x, int distance){
        this.x = x;
        this.distance = distance;
    }
}

public class FindCity {
    public static int N, M, K, X;
    public static int[] visited;
    public static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
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
