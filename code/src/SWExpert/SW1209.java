package SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SW1209 {
    private static int step, max, tc = 10, maxSize = 100;
    private static int[][] arr = new int[maxSize][maxSize];

    public static int sumRow(){
        int sumRow = 0, maxRow = 0;

        for(int i = 0; i < maxSize; i++){
            for(int j = 0; j < maxSize; j++){
                sumRow += arr[i][j];
            }
            maxRow = Math.max(maxRow, sumRow);
            sumRow = 0;
        }

        return maxRow;
    }

    public static int sumCol(){
        int sumCol = 0, maxCol = 0;

        for(int i = 0; i < maxSize; i++){
            for(int j = 0; j < maxSize; j++){
                sumCol += arr[j][i];
            }
            maxCol = Math.max(maxCol, sumCol);
            sumCol = 0;
        }

        return maxCol;
    }

    public static int sumCross(){
        int sumCross = 0, maxCross = 0;

        for(int i = 0; i < maxSize; i++){
            maxCross += arr[i][i];
        }

        for(int i = 99; i >= 0; i--){
            sumCross += arr[99-i][i];
        }

        return Math.max(maxCross, sumCross);
    }

    public static void findLarge(){
        max = Integer.MIN_VALUE;

        max = Math.max(max, sumRow());
        max = Math.max(max, sumCol());
        max = Math.max(max, sumCross());
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int t = 0; t < tc; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            step = Integer.parseInt(st.nextToken());

            for(int i = 0; i < maxSize; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < maxSize; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            findLarge();

            System.out.println("#" + step + " " + max);
        }

    }
}
