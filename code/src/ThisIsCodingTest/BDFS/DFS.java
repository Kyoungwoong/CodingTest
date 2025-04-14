package ThisIsCodingTest.BDFS;

import java.util.*;

public class DFS {
    public static LinkedList<Integer>[] graph = new LinkedList[9];
    public static Stack<Integer> s = new Stack<>();
    public static boolean[] visited = new boolean[9];

    public static void dfs_iterative(int start) {
        Arrays.fill(visited, false);
        s.add(start);

        while (!s.isEmpty()) {
            int cur = s.pop();
            if (visited[cur]) {
                continue;
            }
            visited[cur] = true;
            System.out.print(cur + " ");

            List<Integer> neighbors = graph[cur];
            Collections.reverse(neighbors);

            for (int next : neighbors) {
                if (!visited[next]) {
                    s.push(next);
                    s.add(next);
                }
            }
        }
    }

    public static void dfs_recursive(int start){
        System.out.print(start + " ");
        visited[start] = true;
        s.add(start);
        for(int i = 0; i < graph[start].size(); i++){
            int cur = graph[start].get(i);
            if(visited[cur]) continue;
            dfs_recursive(cur);
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

        dfs_recursive(1);
        System.out.println();
        dfs_iterative(1);

    }
}


