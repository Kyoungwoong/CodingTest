package CodeTree.IntermediateLow.Simulation.second;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Convey {
    private static int MAXSIZE = 200;
    private static int n, t;
    private static int[][] arr = new int[2][MAXSIZE];

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        for(int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            if(i == 0) {
                for(int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            else{
                for(int j = n-1; j >= 0; j--) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

        int temp;
        for(int i = 1; i <= t; i++) {
            temp = arr[1][0];

            for(int j = 1; j < n; j++) {
                arr[1][j-1] = arr[1][j];
            }

            arr[1][n-1] = arr[0][n-1];

            for(int j = n-1; j > 0; j--) {
                arr[0][j] = arr[0][j-1];
            }

            arr[0][0] = temp;
        }

        // print
        for(int i = 0; i < 2; i++) {
            if(i == 0){
                for(int j = 0; j < n; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
            else{
                for(int j = n-1; j >= 0; j--) {
                    System.out.print(arr[i][j] + " ");
                }
            }

        }
    }
}