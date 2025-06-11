package SWExpert.DivideConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1248 {
    static class Node {
        int parent, depth;
        int childCnt = 0;

        public Node(int parent, int depth) {
            this.parent = parent;
            this.depth = depth;
        }
    }

    private static int testCase, V, E, a, b;
    private static Node[] parents;
    private static List<List<Integer>> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            init();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                parents[child] = new Node(parent, parents[parent].depth + 1);
                parents[parent].childCnt++;
                list.get(parent).add(child);
            }

            for (int i = 1; i <= V; i++) {
                if (parents[i].parent == i && !list.get(i).isEmpty()) {
                    setDepth(i, 0);
                }
            }

//            for (int i = 1; i <= V; i++) {
//                System.out.print(i + " \t");
//            }
//            System.out.println();
//            for (int i = 1; i <= V; i++) {
//                System.out.print(parents[i].parent + " \t");
//            }
//            System.out.println();
//            for (int i = 1; i <= V; i++) {
//                System.out.print(parents[i].depth + " \t");
//            }
//            System.out.println();
//            for (int i = 1; i <= V; i++) {
//                System.out.print(parents[i].childCnt + " \t");
//            }

            int grand = findCommonGrand();
            int childCnt = findChildCnt(grand);

            sb.append("#").append(t).append(" ").append(grand).append(" ").append(childCnt);
            if (t != testCase) {
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private static int findChildCnt(int root) {
        int cnt = 1;
        Queue<Integer> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int parent = q.poll();

            for (int child : list.get(parent)) {
                cnt++;
                q.add(child);
            }
        }
        return cnt;
    }

    private static int findCommonGrand() {
        int n1 = a;
        int n2 = b;

        while (n1 != n2) {
            while(parents[n1].depth > parents[n2].depth) {
                n1 = parents[n1].parent;
            }
            while (parents[n1].depth < parents[n2].depth) {
                n2 = parents[n2].parent;
            }

            if(n1 != n2) {
                n1 = parents[n1].parent;
                n2 = parents[n2].parent;
            }
        }
        return n1;
    }

    private static void init() {
        parents = new Node[V + 1];
        for (int i = 0; i <= V; i++) {
            parents[i] = new Node(i, 0);
        }

        list = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            list.add(new ArrayList<>());
        }
    }

    private static void setDepth(int curr, int depth) {
        parents[curr].depth = depth;
        for (int child : list.get(curr)) {
            setDepth(child, depth + 1);
        }
    }
}

/*
2
13 12 8 13
1 2 1 3 2 4 3 5 3 6 4 7 7 12 5 9 5 8 6 10 6 11 11 13
10 9 2 10
1 2 1 3 3 4 4 5 5 6 6 7 7 8 8 9 9 10
50 49 24 31
31 7 2 17 27 32 14 30 1 21 45 26 44 27 39 11 26 3 48 6 3 44 2 49 42 13 48 8 23 33 11 10 8 42 41 31 17 4 8 22 25 23 21 41 28 25 13 16 46 2 31 35 42 19 32 18 27 50 45 15 28 20 46 28 44 40 40 5 15 48 9 34 1 46 17 29 35 36 21 45 14 37 23 14 6 39 11 9 19 24 26 47 16 38 40 12 47 43
 */

/**
 #1 3 8
 #2 1 10
 #3 21 35
 #4 1 100
 #5 168 107
 #6 1 500
 #7 398 840
 #8 747 1359
 #9 498 3141
 #10 7165 2435
 */