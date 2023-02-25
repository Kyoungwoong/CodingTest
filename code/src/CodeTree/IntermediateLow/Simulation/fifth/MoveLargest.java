package CodeTree.IntermediateLow.Simulation.fifth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class MoveLargest {
    private static int MAXSIZE = 20;
    private static int n, m, t;
    private static int[][] arr = new int[MAXSIZE][MAXSIZE];
    private static int[][] marble = new int[MAXSIZE][MAXSIZE];
    private static int[][] nextMarble = new int[MAXSIZE][MAXSIZE];

    public static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static void move(int x, int y){

        int max = -1;
        //          상  하  좌  우
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int nextX = 0, nextY = 0, curX = 0, curY = 0;
        for(int i = 0; i < 4; i++) {
            curX = x + dx[i];
            curY = y + dy[i];

            if(!isRange(curX, curY)){
                continue;
            }

            if(max < arr[curX][curY]){
                nextX = curX;
                nextY = curY;
                max = arr[curX][curY];
            }

        }

        nextMarble[nextX][nextY]++;
        nextMarble[x][y]--;


        // // 잠시 테스트
        // for(int i = 0; i < n; i++) {
        //     for(int j = 0; j < n; j++) {
        //         System.out.print(nextMarble[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();


    }
    public static void moveMarble(int t){

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) {
                nextMarble[i][j] = marble[i][j];
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(marble[i][j] == 1) {
                    move(i, j);
                }
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) {
                marble[i][j] = nextMarble[i][j];
            }
        }

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                if(marble[i][j] >= 2)
                    marble[i][j] = 0;
        // System.out.println("round " + t+1);
        // System.out.println("marbel");
        // // 잠시 테스트
        // for(int i = 0; i < n; i++) {
        //     for(int j = 0; j < n; j++) {
        //         System.out.print(marble[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();
    }

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        // 값 초기화
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 구슬 위치
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            marble[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1]++;
        }

        for(int i = 0; i < t; i++) {
            moveMarble(i);
        }

        int ans = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                ans += marble[i][j];
            }
        }
        System.out.println(ans);
    }
}