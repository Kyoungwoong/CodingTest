package CodeTree.IntermediateLow.BFS.first;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

//class Pair{
//    int x, y;
//    public Pair(int x, int y){
//        this.x = x;
//        this.y = y;
//    }
//}

public class FindPath {
    private static int n, k, count = 0;
    private static int[][] arr = new int[100][100];
    private static int[][] ans = new int[100][100];
    private static boolean[][] visited = new boolean[100][100];
    private static Queue<Pair> q = new LinkedList<>();

    public static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static boolean canGo(int x, int y){
        if(!isRange(x, y)) return false;
        if(visited[x][y] || arr[x][y] == 1) return false;
        return true;
    }

    public static void push(Pair pair){
        ans[pair.x][pair.y]++;
        visited[pair.x][pair.y] = true;
        q.add(pair);
    }

    public static void findPath(){
        int[] dx = new int[]{0, 1,  0, -1};
        int[] dy = new int[]{1, 0, -1,  0};

        while(!q.isEmpty()){
            Pair cur = q.poll();
            for(int idx = 0; idx < 4; idx++) {
                int nextX = cur.x + dx[idx];
                int nextY = cur.y + dy[idx];
                if(canGo(nextX, nextY)) {
                    Pair newPair = new Pair(nextX, nextY);
                    push(newPair);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int curX = Integer.parseInt(st.nextToken())-1;
            int curY = Integer.parseInt(st.nextToken())-1;
            Pair newPair = new Pair(curX, curY);

            push(newPair);
            // System.out.println("newPair: " + newPair.x + " " + newPair.y);
            findPath();
        }

        for(int i = 0; i<n; i++) {
            for(int j = 0; j < n; j++) {
                if(ans[i][j] >0){
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}