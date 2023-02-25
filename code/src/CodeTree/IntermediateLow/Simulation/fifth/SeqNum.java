package CodeTree.IntermediateLow.Simulation.fifth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SeqNum {
    private static int MAXSIZE = 20;
    private static int n, m;
    private static int[][] arr = new int[MAXSIZE][MAXSIZE];

    public static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static void moveCount(){
        int i = 1;
        while(i != n*n + 1){

            int row = 0, col = 0;
            for(int s = 0; s < n; s++) {
                for(int p = 0; p < n; p++) {
                    if(arr[s][p] == i){
                        row = s;
                        col = p;
                    }
                }
            }

            int max = -1;
            //        북  북동  동 남동 남 남서 서 북서
            int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
            int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

            int nextX = 0, nextY = 0, curX = 0, curY = 0;
            for(int dir = 0; dir < 8; dir++) {
                nextX = row + dx[dir];
                nextY = col + dy[dir];

                if(!isRange(nextX, nextY)){
                    continue;
                }

                if(max < arr[nextX][nextY]) {
                    curX = nextX;
                    curY = nextY;
                    max = arr[nextX][nextY];
                }
            }

            int temp = arr[row][col];
            arr[row][col] = arr[curX][curY];
            arr[curX][curY] = temp;

            i++;
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
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < m; i++) {
            moveCount();
        }

        for(int i = 0; i<n; i++) {
            for(int j = 0; j<n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}