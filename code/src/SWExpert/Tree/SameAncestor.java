package SWExpert.Tree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class SameAncestor {
    private static int V, E, first, second, ancestor, depth=0;
    private static ArrayList<Integer>[] revtree = new ArrayList[10001];
    private static ArrayList<Integer>[] tree = new ArrayList[10001];

    public static void findSameAncestor(){

//        for(int i = 0; i < (int)revtree[first].size(); i++){
//            System.out.print(revtree[first].get(0) + " ");
//        }
//        System.out.println();

        int[] cnt = new int[10001];
        int parent = revtree[first].get(0);

//        System.out.print(parent + " ");

        cnt[parent]++;

        while(revtree[parent].size() != 0){
            parent = revtree[parent].get(0);
            cnt[parent]++;

//            System.out.print(parent + " ");
        }
//        System.out.println();

        parent = revtree[second].get(0);
//        System.out.print(parent + " ");
        if(cnt[parent] != 0){
            ancestor = parent;
            return;
        }

        while(revtree[parent].size() != 0){
            parent = revtree[parent].get(0);
//            System.out.print(parent + " ");
            if(cnt[parent] != 0){
                ancestor = parent;
                return;
            }
            cnt[parent]++;
        }
    }

    public static void findDepth(int node){
        depth++;
        for(int i = 0; i < tree[node].size(); i++){
            findDepth(tree[node].get(i));

        }
    }

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("SW_Testcase/Tree/same.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            first = Integer.parseInt(st.nextToken());
            second = Integer.parseInt(st.nextToken());

            for(int i = 1; i <= V; i++){
                revtree[i] = new ArrayList<>();
                tree[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < E; i++){
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                revtree[child].add(parent);
                tree[parent].add(child);
            }

            findSameAncestor();
            findDepth(ancestor);

            System.out.println("#" + test_case + " " + ancestor + " " + depth);
//            System.out.println("#" + 1 + " " + ancestor + " " + depth);

            for(int i = 1; i <= V; i++){
                revtree[i].clear();
                tree[i].clear();
            }
            depth = 0;
            ancestor = -1;
        }
    }

}
