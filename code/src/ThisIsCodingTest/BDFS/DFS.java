package ThisIsCodingTest.BDFS;

import java.util.LinkedList;
import java.util.Stack;

public class DFS {
    public static LinkedList<Integer>[] graph = new LinkedList[9];
    public static Stack<Integer> s = new Stack<>();
    public static boolean[] visited = new boolean[9];

    public static void dfs(int start){
        System.out.print(start + " ");
        visited[start] = true;
        s.add(start);
        for(int i = 0; i < graph[start].size(); i++){
            int cur = graph[start].get(i);
            if(visited[cur]) continue;
            dfs(cur);
        }
    }

    public static void main(String[] args) {
        for(int i = 1; i <= 8; i++){
            graph[i] = new LinkedList<>();
            switch (i){
                case 1:
                    graph[i].add(2);
                    graph[i].add(3);
                    graph[i].add(8);
                    break;
                case 2:
                    graph[i].add(1);
                    graph[i].add(7);
                    break;
                case 3:
                    graph[i].add(1);
                    graph[i].add(4);
                    graph[i].add(5);
                    break;
                case 4:
                    graph[i].add(3);
                    graph[i].add(5);
                    break;
                case 5:
                    graph[i].add(3);
                    graph[i].add(4);
                    break;
                case 6:
                    graph[i].add(7);
                    break;
                case 7:
                    graph[i].add(2);
                    graph[i].add(6);
                    graph[i].add(8);
                    break;
                case 8:
                    graph[i].add(1);
                    graph[i].add(7);
                    break;
            }
        }

        dfs(1);

    }
}


