package CodeTree.IntermediateLow.DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class FindSafeArea {
    private static int n, m, K, max = -1, area = 0;
    private static int[][] house = new int[50][50];
    private static boolean[][] visited = new boolean[50][50];

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    public static boolean canGo(int x, int y, int k) {
        if(!inRange(x, y))
            return false;

        if(visited[x][y] || house[x][y] <= k)
            return false;

        return true;
    }

    public static void DFS(int x, int y, int k){

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        for(int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if(canGo(newX, newY, k)){
                visited[newX][newY] = true;
                DFS(newX, newY, k);
            }
        }

    }

    public static void findSafeArea(int k) {

        area = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                visited[i][j] = false;
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++){
                if(canGo(i, j, k)){
                    visited[i][j] = true;
                    area++;
                    DFS(i, j, k);
                }
            }
        }
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
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int k = 1; k <= 100; k++) {
            findSafeArea(k);
            if(max < area){
                K = k;
                max = area;
            }
        }

        System.out.print(K + " ");
        System.out.print(max);
    }
}