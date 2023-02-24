package CodeTree.IntermediateLow.Simulation.second;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Wind2D {
    private static int N, M, Q;
    private static int r1, c1, r2, c2;
    private static int[][] arr = new int[100][100];
    private static int[][] state = new int[100][100];

    public static void calAverage(int row, int col){
        int sum = 0, count = 0;
        if(row - 1 >= 0) {
            sum += state[row-1][col];
            count++;
        }
        if(row + 1 < N) {
            sum += state[row+1][col];
            count++;
        }
        if(col-1 >= 0) {
            sum += state[row][col-1];
            count++;
        }
        if(col+1 < M) {
            sum += state[row][col+1];
            count++;
        }
        sum += state[row][col];
        count++;

        arr[row][col] = (sum / count);
    }

    public static void changeState(int r1, int c1, int r2, int c2) {
        int temp1, temp2;

        // 복사 진행하기
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                state[i][j] = arr[i][j];
            }
        }

        // shift right
        temp1 = state[r1-1][c2-1]; // 5
        for(int i = c2-1; i > c1-1; i--) {
            state[r1-1][i] = state[r1-1][i-1];
        }

        //shift down
        temp2 = state[r2-1][c2-1]; // 8
        for(int i = r2-1; i > r1; i--){
            state[i][c2-1] = state[i-1][c2-1];
        }
        state[r1][c2-1] = temp1;

        // shift left
        temp1 = state[r2-1][c1-1]; // 2
        for(int i = c1-1; i < c2-2; i++) {
            state[r2-1][i] = state[r2-1][i+1];
        }
        state[r2-1][c2-2] = temp2;

        // shift top
        for(int i = r1-1; i < r2-1; i++) {
            state[i][c1-1] = state[i+1][c1-1];
        }
        state[r2-2][c1-1] = temp1;

        for(int i = r1-1; i < r2; i++) {
            for(int j = c1-1; j < c2; j++) {
                calAverage(i,j);
            }
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
            r1 = Integer.parseInt(st.nextToken());
            c1 = Integer.parseInt(st.nextToken());
            r2 = Integer.parseInt(st.nextToken());
            c2 = Integer.parseInt(st.nextToken());

            changeState(r1, c1, r2, c2);

        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}