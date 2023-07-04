package ThisIsCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

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
