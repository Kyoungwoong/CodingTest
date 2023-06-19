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
        for(int i = 0 ; i < N; i++){
            graph[i] = new ArrayList<>();
        }
        visited = new int[N];

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            graph[s].add(e);
        }

        Queue<Integer> q = new LinkedList<>();
        visited[X] = 0;
        for(int i = 0; i < graph[X].size(); i++){
            q.add(graph[X].get(i));
            visited[graph[X].get(i)] = 1;
        }

        while(!q.isEmpty()){
            int cur = q.poll();
            for(int i = 0; i < graph[cur].size(); i++){
                if(visited[graph[cur].get(i)] != 0){
                    continue;
                }
                q.add(graph[cur].get(i));
                visited[graph[cur].get(i)] = visited[cur] + 1;
            }
        }

        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0; i < N; i++){
            if(visited[i] == K){
                arr.add(i+1);
            }
        }
        if(arr.size() == 0){
            System.out.println(-1);
        }else{
            for(int i = 0; i < arr.size(); i++){
                System.out.println(arr.get(i));
            }
        }
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
