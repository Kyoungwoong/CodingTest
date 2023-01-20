package CodeTree.IntermediateLow.BFS.first;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

//class Pair{
//    int x, y;
//    public Pair(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//}

public class BFS {
    private static int n, m, ans = 0;
    private static int[][] arr = new int[100][100];
    private static boolean[][] visited = new boolean[100][100];
    private static Queue<Pair> q = new LinkedList<>();

    public static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public static boolean canGo(int x, int y){
        if(!isRange(x, y)) return false;
        if(visited[x][y] || arr[x][y] == 0) return false;

        return true;
    }

    public static void push(int x, int y){
        visited[x][y] = true;
        if(x == n-1 && y == m-1) {
            ans = 1;
        }
        q.add(new Pair(x, y));
    }

    public static void BFS(){
        int[] dx = new int[]{0, 1,  0, -1};
        int[] dy = new int[]{1, 0, -1,  0};

        while(!q.isEmpty()){
            Pair cur = q.poll();
            int x = cur.x, y = cur.y;

            for(int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];
                if(canGo(nextX, nextY)){
                    push(nextX, nextY);
                    // System.out.println("curX: " + nextX + " curY: " + nextY);
                }
            }
        }
    }

    public static void findMiro(int x, int y){
        push(x, y);
        BFS();
    }

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findMiro(0, 0);
        System.out.println(ans);
    }
}