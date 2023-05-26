package ThisIsCodingTest.ShortPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentMap;

class Message implements Comparable<Message> {
    int index, dist;
    public Message(int index, int dist){
        this.index = index;
        this.dist = dist;
    }

    @Override
    public int compareTo(Message m){
        return this.dist - m.dist;
    }

}

public class Pass {
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
