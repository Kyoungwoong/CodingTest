package CodeTree.IntermediateLow.Simulation.third;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BestExplosion {
    private static int MAXSIZE = 200;
    private static int n, r, c;
    private static int[][] arr = new int[MAXSIZE][MAXSIZE];

    public static int findPair(){
        // 아래 오른쪽 만 확인하기
        int count = 0;
        // down
        for(int row = 0; row < n; row++){
            for(int col = 0; col < n; col++) {
                if(isRange(row+1, col)){
                    if(arr[row][col] == arr[row+1][col] && arr[row][col] != 0) {
                        count++;
                    }
                }
                if(isRange(row, col+1)) {
                    if(arr[row][col] == arr[row][col+1] && arr[row][col] != 0){
                        count++;
                    }
                }
            }
        }

        return count;
    }

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

    public static int bomb(int row, int col){
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
        // for(int i = 0; i< n; i++){
        //     for(int j = 0; j< n; j++) {
        //         System.out.print(arr[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();
        return findPair();
    }

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[][] std = new int[n][n];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                std[i][j] = arr[i][j];
            }
        }

        int max = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                max = Math.max(max, bomb(i, j));
                for(int s = 0; s < n; s++) {
                    for(int p = 0; p < n; p++) {
                        arr[s][p] = std[s][p];
                    }
                }
            }
        }
        System.out.println(max);


    }
}