package ThisIsCodingTest.ShortPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 주의 사항
// 연결된 회사끼리는 양방향 가능..
public class Future {
    static class Node implements Comparable<Node> {
        int desc, distance;

        public Node(int desc, int distance) {
            this.desc = desc;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node node) {
            return this.distance - node.distance;
        }
    }
    public static int N, M, X, K, INF = (int)1e9;
    public static int[][] dist = new int[100][100];
    public static int[] d = new int[100];
    public static boolean[] visited = new boolean[100];
    private static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
//        prev();
        oct15();
    }

    private static void oct15() throws IOException {
        // 특정 회사 끼리 서로 도로를 통해 연결
        // A는 1번 회사에 있고 X번 회사에 방문해 물건을 판매하려고 함.
        // K번 회사에 존재하는 사람과 소개팅
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int desc = Integer.parseInt(st.nextToken());

            graph.get(src).add(desc);
        }
        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Arrays.fill(d, Integer.MAX_VALUE);

        visited[1] = true;
        dijkstra(1);

        if (d[X] == Integer.MAX_VALUE || d[K] == Integer.MAX_VALUE) {
            System.out.println("-1 = " + -1);
        } else {
            System.out.println("d[X] = " + (d[K] + d[X]));
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        d[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (d[cur.desc] < cur.distance) {
                continue;
            }

            for (int desc : graph.get(cur.desc)) {
                if (d[desc] > d[cur.desc] + 1) {
                    d[desc] = d[cur.desc] + 1;
                    pq.add(new Node(desc, d[cur.desc] + 1));
                }
            }
        }
    }

    private static void prev() throws IOException {
        // A는 1번 회사에 위치
        // X번 회사에 방문해 물건을 판해
        // K번 회사에 소개팅 1 ->..K..-> X
        // 도달할 수 없으면 -1
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                dist[i][j] = INF;
            }
            dist[i][i]=0;
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            dist[a][b] = 1;
            dist[b][a] = 1;
        }
        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken())-1;
        K = Integer.parseInt(st.nextToken())-1;

        for(int k = 0; k < N; k++){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(dist[i][j]+"\t");
            }
            System.out.println();
        }

        int result = dist[0][K] + dist[K][X];
        if(result >= INF){
            System.out.println("-1 = " + -1);
        }else{
            System.out.println("result = " + result);
        }
    }
}
/*
5 7
1 2
1 3
1 4
2 4
3 4
3 5
4 5
4 5

4 2
1 3
2 4
3 4
 */
