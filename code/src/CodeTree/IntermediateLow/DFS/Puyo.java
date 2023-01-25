package CodeTree.IntermediateLow.DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Puyo {
    private static int n, block = 1, maxBlock = -1, area = 0;
    private static int[][] arr = new int[100][100];
    private static boolean[][] visited = new boolean[100][100];

    public static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static boolean canGo(int x, int y, int std){
        if(!isRange(x, y)) return false;
        if(visited[x][y] || arr[x][y] != std) return false;

        return true;
    }

    public static void DFS(int x, int y, int std) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        visited[x][y] = true;

        for(int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if(canGo(nextX, nextY, std)){
                block++;
                DFS(nextX, nextY, std);
            }
        }
    }

    public static void puyo(int x, int y, int std) {
        block = 1;
        DFS(x, y, std);

        maxBlock = Math.max(block, maxBlock);
        if(block >= 4) {
            area++;
        }
    }

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j]) {
                    int num = arr[i][j];
                    puyo(i, j, num);
                }
            }
        }
        System.out.print(area + " " + maxBlock);
    }
}