package CodeTree.IntermediateLow.Simulation.second;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Wind1D {
    private static int N, M, Q;
    private static char wind;
    private static int MAXSIZE = 100;
    private static int[][] arr = new int[MAXSIZE][MAXSIZE];

    public static void changeState(int row, char dir, int state){
        int temp;
        boolean check = false;

        if(dir == 'L') {
            temp = arr[row-1][M-1];
            for(int i = M-1; i > 0; i--) {
                arr[row-1][i] = arr[row-1][i-1];
            }
            arr[row-1][0] = temp;
            dir = 'R';
        }else{
            temp = arr[row-1][0];
            for(int i = 0; i < M-1; i++) {
                arr[row-1][i] = arr[row-1][i+1];
            }
            arr[row-1][M-1] = temp;
            dir = 'L';
        }

        // for(int i = 0; i < N; i++) {
        //     for(int j = 0; j < M; j++) {
        //         System.out.print(arr[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();

        /*
         * state == 0 맨 처음에 불려진것
         * state == 1 아래에서 부른거
         * state == 2 위에서 부른 것.
         */
        switch(state) {
            case 0:
                // 위쪽 검사
                for(int i = 0; i < M; i++) {
                    if(row-2 < 0) {
                        break;
                    }
                    if(arr[row-1][i] == arr[row-2][i]){
                        check = true;
                        break;
                    }
                }
                if(check){
                    changeState(row-1, dir, 1);
                    check = false;
                }

                // 아래쪽 검사
                for(int i = 0; i < M; i++) {
                    if(row >= N){
                        break;
                    }
                    if(arr[row-1][i] == arr[row][i]){
                        check = true;
                        break;
                    }
                }
                if(check){
                    changeState(row+1, dir, 2);
                }
                break;
            case 1:
                // 위쪽 검사
                for(int i = 0; i < M; i++) {
                    if(row-2 < 0) {
                        break;
                    }
                    if(arr[row-1][i] == arr[row-2][i]){
                        check = true;
                        break;
                    }
                }
                if(check){
                    changeState(row-1, dir, 1);
                    check = false;
                }
                break;
            case 2:
                // 아래쪽 검사
                for(int i = 0; i < M; i++) {
                    if(row >= N){
                        break;
                    }
                    if(arr[row-1][i] == arr[row][i]){
                        check = true;
                        break;
                    }
                }
                if(check){
                    changeState(row+1, dir, 2);
                }
                break;
        }

    }

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            wind = st.nextToken().charAt(0);
            // System.out.println("row is " + row + " wind is " + wind);
            changeState(row, wind, 0);
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

    }
}