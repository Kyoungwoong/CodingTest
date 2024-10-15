package ThisIsCodingTest.ShortPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentMap;

public class Pass {
    static class Node implements Comparable<Node>{
        int index, distance;
        public Node(int index, int distance){
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node n){
            return this.distance - n.distance;
        }
    }

    public static int N, M, C, X, Y, Z;
    public static ArrayList<ArrayList<Node>> cities = new ArrayList<ArrayList<Node>>();
    public static PriorityQueue<Node> pq = new PriorityQueue<>();
    public static int[] distance;

    public static void dijkstra(int start){
        pq.add(new Node(start, 0));
        distance[start] = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int dist = cur.distance;
            int idx = cur.index;

            if(distance[idx] < dist){
                continue;
            }

            for(int i = 0; i < cities.get(idx).size(); i++){
                int cost = distance[idx] + cities.get(idx).get(i).distance;
                if(cost < distance[cities.get(idx).get(i).index]){
                    distance[cities.get(idx).get(i).index] = cost;
                    pq.offer(new Node(cities.get(idx).get(i).index, cost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        prev();

        oct15();
    }

    private static void oct15() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            cities.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int desc = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            cities.get(src).add(new Node(desc, distance));
        }
        distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[C] = 0;

        oct_Dijkstra(C);

        int ans = 0;
        int time = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            if (i != C && distance[i] != Integer.MAX_VALUE) {
                time = Math.max(time, distance[i]);
                ans++;
            }
        }
        System.out.println("ans = " + ans);
        System.out.println("time = " + time);
    }

    private static void oct_Dijkstra(int start) {
        pq.add(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (distance[cur.index] < cur.distance) {
                continue;
            }

            for (Node desc : cities.get(start)) {
                if (distance[desc.index] > distance[cur.index] + desc.distance) {
                    distance[desc.index] = distance[cur.index] + desc.distance;
                    pq.add(new Node(desc.index, distance[cur.index] + desc.distance));
                }
            }

        }
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        distance = new int[N+1];
        for(int i = 0; i <= N; i++){
            distance[i] = (int) 1e9;
        }

        for(int i = 0; i <= N; i++){
            cities.add(new ArrayList<Node>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            cities.get(Integer.parseInt(st.nextToken())).add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        dijkstra(C);

        int cnt = 0;
        int max_distance = 0;
        for(int i = 0; i <= N; i++){
            if(distance[i] != (int) 1e9){
                cnt+=1;
                max_distance = Math.max(max_distance, distance[i]);
            }
        }

        System.out.println("cnt = " + cnt+ " " + max_distance) ;
    }
}
/*
3 2 1
1 2 4
1 3 2
 */
