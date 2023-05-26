package ThisIsCodingTest.ShortPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



public class FixedDijkstra {
    public static final int INF = Integer.MAX_VALUE;
    public static int N, M, START;
    // 각 노드에 연결되어 있는 노드에 대한 정보를 담는 배열
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    // 방문한 적이 있는지 체크하는 목적의 배열 만들기
    public static boolean[] visited = new boolean[100001];
    // 최단 거리 테이블 만들기
    public static int[] d = new int[100001];

    public static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static int get_smallest_node(){
        int min_value = INF;
        int min_idx = 100002;
        for(int i = 1; i <= N; i++){
            if(d[i] < min_value && !visited[i]){
                min_value = d[i];
                min_idx = i;
            }
        }
        return min_idx;
    }

    public static void dijkstra(int start){
        d[start] = 0;
//        visited[start] = true;
        pq.add(new Node(start, 0));

        // 시작 노드에 대해 거리 초기화
        while(!pq.isEmpty()){
            Node now = pq.poll();
            int dist = now.distance;
            int idx = now.index;

            if(d[idx] < dist){
                continue;
            }

            for(int i = 0; i < graph.get(idx).size(); i++){
                int cost = d[idx] + graph.get(idx).get(i).distance;
                if (cost < d[graph.get(idx).get(i).index]) {
                    d[graph.get(idx).get(i).index] = cost;
                    pq.offer(new Node(graph.get(idx).get(i).index, cost));
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        START = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<Node>());
        }

        // 모든 간선 정보를 입력받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // a번 노드에서 b번 노드로 가는 비용이 c라는 의미
            graph.get(a).add(new Node(b, c));
        }

        // 최단 거리 테이블을 모두 무한으로 초기화
        Arrays.fill(d, INF);

        // 다익스트라 알고리즘을 수행
        dijkstra(START);

        for (int i = 1; i <= N; i++) {
            // 도달할 수 없는 경우, 무한(INFINITY)이라고 출력
            if (d[i] == INF) {
                System.out.println("INFINITY");
            }
            // 도달할 수 있는 경우 거리를 출력
            else {
                System.out.println(d[i]);
            }
        }

    }
}
/*
6 11
1
1 2 2
1 3 5
1 4 1
2 3 3
2 4 2
3 2 3
3 6 5
4 3 3
4 5 1
5 3 1
5 6 2
 */
