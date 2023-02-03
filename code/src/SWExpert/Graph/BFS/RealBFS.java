package SWExpert.Graph.BFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class RealBFS {
    private static int n, ans;
    private static ArrayList<Integer>[] tree;
    private static Queue<Integer> q = new LinkedList<>();
    private static int[][] depth;

    public static void edgeCount(){
        ans = 0;
        q.add(1);

        int to = 1, lca;
        while(!q.isEmpty()){
            int cur = q.poll();
            lca = findLCA(cur, to);
            if(lca == -1){
                lca = 1;
            }
            ans += depth[0][cur] - depth[0][lca];
            ans += depth[0][to] - depth[0][lca];

            to = cur;

            for(int i = 0; i < (int)tree[cur].size(); i++){
                q.add(tree[cur].get(i));
            }
        }

    }

    public static int findLCA(int a, int b){
        if(depth[0][a] == depth[0][b]){
            int dep = depth[0][a];
            for(int i = 1; i < dep; i++){
                if(depth[i][a] == depth[i][b]){
                    return depth[i][a];
                }
            }
        }else{
            if(depth[0][a] > depth[0][b]){
                int temp = a;
                a = b;
                b = temp;
            }

            while(depth[0][a] != depth[0][b]){
                b = depth[1][b];
            }
            if(b == a){
                return b;
            }
            int dep = depth[0][a];
            for(int i = 1; i < dep; i++){
                if(depth[i][a] == depth[i][b]){
                    return depth[i][a];
                }
            }
        }
        return -1;
    }

    public static void makeLCA(){
        for(int i = 1; i <= n; i++){
            for(int j = 2; j <= n; j++){
                int parent = depth[i][j];

                if(parent != -1){
                    if(depth[i][parent] != 0){
                        if(depth[i][parent] == -1){
                            for(int row = i-1; row > 0; row--){
                                if(depth[row][parent] != -1 && depth[row][parent] != 0){
                                    depth[i+1][j] = depth[row][parent];
                                }
                            }
                        }else{
                            depth[i+1][j] = depth[i][parent];
                        }

                    }else{
                        if(depth[i][j] != 1){
                            depth[i+1][j] = 1;
                        }

                    }
                }
            }
        }

        for(int i = 1; i <= n; i++){
            int cnt= 0;
            for(int j = 1; j <= n; j++){
                if(depth[j][i] != -1 && depth[j][i] != 0){
                    cnt++;
                }
            }

            depth[0][i] = cnt;
        }

//        for(int i = 0; i <= n; i++){
//            for(int j = 0; j<= n; j++){
//                System.out.print(depth[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();

    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("SW_Testcase/BFS/real.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++){
            n = Integer.parseInt(br.readLine());

            depth = new int[n+1][n+1];
            for(int i = 0; i <= n; i++){
                for(int j = 0; j<= n; j++){
                    depth[i][j] = -1;
                }
            }
            depth[1][1] = 0;
            tree = new ArrayList[n+1];
            for(int i = 0; i <= n; i++){
                tree[i] = new ArrayList<>();
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 1; i < n; i++){
                int node = Integer.parseInt(st.nextToken());
                depth[1][i+1] = node;
                tree[node].add(i+1);
            }

            makeLCA();
            edgeCount();

            System.out.println("#" + t + " " + ans);

        }
    }
}
