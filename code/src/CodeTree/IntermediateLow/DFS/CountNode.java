package CodeTree.IntermediateLow.DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class CountNode {
    private static int n, m, count = 0;
    public static ArrayList<Integer>[] node = new ArrayList[1001];
    private static boolean[] visited = new boolean[1001];

    public static void countNode(int start){
        // if(node[1].size() == 0) {
        //     return;
        // }
        for(int i = 0; i<(int)node[start].size(); i++) {
            if(visited[node[start].get(i)]) continue;

            visited[node[start].get(i)] = true;
            count++;
            countNode(node[start].get(i));
        }
    }

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= n; i++)
            node[i] = new ArrayList<>();

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            node[node1].add(node2);
            node[node2].add(node1);
        }
        visited[1] = true;
        countNode(1);

        System.out.println(count);
    }
}