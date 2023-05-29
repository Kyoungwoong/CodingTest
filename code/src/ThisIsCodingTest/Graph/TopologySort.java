package ThisIsCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TopologySort {
    public static int V, E;
    public static int[] arr;
    public static boolean[] visited;
    public static Queue<Integer> q = new LinkedList<>();
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

    public static void topology_sort(){
        while(!q.isEmpty()){
            int std = q.poll();
            System.out.print(std + " ");
            for(int i = 0; i < graph.get(std).size(); i++){
                int cur = graph.get(std).get(i);
                arr[cur]--;
            }

            push();
        }
    }

    public static void push(){
        for(int i = 1; i <= V; i++){
            if(arr[i] == 0 && !visited[i]){
                visited[i] = true;
                q.add(i);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        arr = new int[V+1];
        visited = new boolean[V+1];

        // 그래프 초기화
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<Integer>());
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[b]++;
            graph.get(a).add(b);
        }

        push();

        topology_sort();
    }
}
/*
7 8
1 2
1 5
2 3
2 6
3 4
4 7
5 6
6 4
 */
