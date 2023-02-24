package CodeTree.IntermediateLow.Simulation.second;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class TriConvey {
    private static int MAXSIZE = 200;
    private static int n, t;
    private static int[][] arr = new int[3][MAXSIZE];

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        for(int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            if(i != 2) {
                for(int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            else{
                for(int j = n-1; j >= 0; j--) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        } // end initial

        for(int k = 0; k < t; k++) {
            int temp = arr[2][0];
            int temp2 = arr[0][n-1];

            for(int j = n-1; j > 0; j--) {
                arr[0][j] = arr[0][j-1];
            }
            arr[0][0] = temp;

            temp = arr[1][n-1];
            for(int j = n-1; j > 0; j--) {
                arr[1][j] = arr[1][j-1];
            }
            arr[1][0] = temp2;

            for(int j = 0; j < n-1; j++) {
                arr[2][j] = arr[2][j+1];
            }
            arr[2][n-1] = temp;
        }

        for(int i = 0; i < 3; i++){
            if(i != 2) {
                for(int j = 0; j < n; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
            else{
                for(int j = n-1; j >= 0; j--) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
        }


    }
}