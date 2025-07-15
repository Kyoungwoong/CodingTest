package ThisIsCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Planet{
    int x, y, z;

    public Planet(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

class Road implements Comparable<Road>{
    int src, dest, dist;

    public Road(int src, int dest, int dist) {
        this.src = src;
        this.dest = dest;
        this.dist = dist;
    }

    @Override
    public int compareTo(Road road) {
        return this.dist - road.dist;
    }
}

public class Tunnel {

    static class Node implements Comparable<Node> {
        int idx, cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    private static int N;
    private static Planet[] planets;
    private static ArrayList<Road> graph = new ArrayList<>();
    private static int[] parent;

    public static void init() {
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
    }

    public static int findParent(int n) {
        if (parent[n] != n) {
            parent[n] = findParent(parent[n]);
        }

        return parent[n];
    }

    public static void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (a < b) {
            parent[b] = a;
        }else{
            parent[a] = b;
        }
    }

    public static void main(String[] args) throws IOException {
//        prev();
        now();
    }

    static boolean[] visited;
    static List<List<Node>> adj;

    private static void now() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        planets = new Planet[N];
        visited = new boolean[N];

        int[][] coords = new int[N][4]; // [i][0] = index, [i][1~3] = x,y,z

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            planets[i] = new Planet(x, y, z);
            coords[i][0] = i;
            coords[i][1] = x;
            coords[i][2] = y;
            coords[i][3] = z;
        }

        // 간선 정보 생성
        adj = new ArrayList<>();
        for (int i = 0; i < N; i++) adj.add(new ArrayList<>());

        for (int d = 1; d <= 3; d++) {
            final int dim = d;
            Arrays.sort(coords, (a, b) -> Integer.compare(a[dim], b[dim]));
            for (int i = 0; i < N - 1; i++) {
                int u = coords[i][0];
                int v = coords[i + 1][0];
                int cost = Math.abs(coords[i][dim] - coords[i + 1][dim]);
                adj.get(u).add(new Node(v, cost));
                adj.get(v).add(new Node(u, cost));
            }
        }

        // Prim
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));
        int totalCost = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visited[cur.idx]) continue;
            visited[cur.idx] = true;
            totalCost += cur.cost;

            for (Node next : adj.get(cur.idx)) {
                if (!visited[next.idx]) {
                    pq.add(new Node(next.idx, next.cost));
                }
            }
        }

        System.out.println(totalCost);
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        planets = new Planet[N];
        parent = new int[N];
//        for (int i = 0; i < N; i++) {
//            graph.add(new ArrayList<>());
//        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            planets[i] = new Planet(x, y, z);
        }

        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if(i == j) continue;
                graph.add(new Road(i, j, Math.min(
                            Math.min(Math.abs(planets[i].x - planets[j].x), Math.abs(planets[i].y - planets[j].y))
                            , Math.abs(planets[i].z - planets[j].z))
                        ));
            }
        }

        Collections.sort(graph);

        init();
        int ans = 0;

        for (int i = 0; i < graph.size(); i++) {
            Road cur = graph.get(i);
            int src = cur.src;
            int dest = cur.dest;
            int dist = cur.dist;

            if (findParent(src) != findParent(dest)) {
                System.out.println("src: " + src + " dest: " + dest + " dist: " + dist + " ans: " + ans);
                union(src, dest);
                ans += dist;
            }
        }
        for (int i = 0; i < N; i++) {
            System.out.print(parent[i] + " ");
        }
        System.out.println("ans = " + ans);
    }
}
/*
5
11 -15 -15
14 -5 -15
-1 -1 -5
10 -4 -1
19 -4 19
--- 4
 */
