package CodeTree.IntermediateLow.DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class EscapeMiro {
    private static int n, m, ans = 0;
    private static int[][] arr = new int[100][100];
    private static boolean[][] visited = new boolean[100][100];

    public static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public static boolean canGo(int x, int y) {
        if(!isRange(x, y)){
            return false;
        }
        else{
            if(arr[x][y] == 0 || visited[x][y]) {
                return false;
            }
        }

        return true;
    }

    public static void escapeMiro(int curX, int curY) {
        if(curX == n-1 && curY == m-1){
            ans = 1;
            return;
        }

        int[] dx = {1, 0};
        int[] dy = {0, 1};

        visited[curX][curY] = true;

        for(int i = 0; i < 2; i++){
            int nextX = curX + dx[i];
            int nextY = curY + dy[i];
            if(canGo(nextX, nextY)){
                escapeMiro(nextX, nextY);
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
            for(int j = 0; j < m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        escapeMiro(0, 0);
        System.out.println(ans);
    }
}