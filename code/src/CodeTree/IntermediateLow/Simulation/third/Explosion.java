package CodeTree.IntermediateLow.Simulation.third;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Explosion {
    private static int MAXSIZE = 200;
    private static int n, r, c;
    private static int[][] arr = new int[MAXSIZE][MAXSIZE];

    public static void checkArr(){
        for(int j = 0; j < n; j++) {
            // 열 기준
            int[] temp = new int[n];
            int tempRow = 0;
            for(int i = 0; i < n; i++) {
                if(arr[i][j] == 0) {
                    continue;
                }
                else{
                    temp[tempRow++] = arr[i][j];
                }
            }

            for(int i = 0; i < n; i++) {
                if(tempRow-1-i < 0) {
                    arr[n-1-i][j] = 0;
                }
                else{
                    arr[n-1-i][j] = temp[tempRow-1-i];
                }

            }
        }
    }

    public static boolean isRange(int x, int y) {
        if(x >= 0 && x < n && y >= 0 && y < n) {
            return true;
        }
        return false;
    }

    public static void bomb(int row, int col){
        int weight = arr[row][col];
        arr[row][col] = 0;

        for(int i = 1; i < weight; i++) {

            // 상
            if(isRange(row-i, col)){
                // System.out.print(row-i + " " + col);
                arr[row-i][col] = 0;
            }
            // 하
            if(isRange(row+i, col)){
                // System.out.print(" "+ row+i + " " + col);
                arr[row+i][col] = 0;
            }
            // 좌
            if(isRange(row, col-i)){
                // System.out.print(" "+ row+i + " " + col);
                arr[row][col-i] = 0;
            }
            //우
            if(isRange(row, col+i)){
                arr[row][col+i] = 0;
            }
        }

        checkArr();
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        bomb(r-1, c-1);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

    }
}