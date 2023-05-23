package ThisIsCodingTest.BDFS;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static LinkedList<Integer>[] graph = new LinkedList[9];
    public static boolean[] visited = new boolean[9];
    public static Queue<Integer> q = new LinkedList<>();

    public static void bfs(int start){
        q.add(start);
        visited[start] = true;
        while(!q.isEmpty()){
            int poll = q.poll();
            System.out.print(poll + " ");
            for(int i = 0; i < graph[poll].size(); i++){
                int cur = graph[poll].get(i);
                if(!visited[cur]){
                    q.add(cur);
                }
                visited[cur] = true;
            }
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
        bfs(1);
    }
}
