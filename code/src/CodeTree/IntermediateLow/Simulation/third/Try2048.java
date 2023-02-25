package CodeTree.IntermediateLow.Simulation.third;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Try2048 {
    private static int n = 4;
    private static int[][] arr = new int[4][4];
    private static char cmd;

    public static void sum2048(int[][] arr, char cmd){
        switch(cmd){
            case 'R':
                for(int i = 0; i < n; i++) {
                    for(int j = n-1; j > 0; j--) {
                        if(arr[i][j] == arr[i][j-1]) {
                            arr[i][j] += arr[i][j];
                            arr[i][j-1] = 0;
                        }
                    }
                }
                break;
            case 'L':
                for(int i = 0; i<n; i++) {
                    for(int j = 0; j < n-1; j++) {
                        if(arr[i][j] == arr[i][j+1]){
                            arr[i][j] += arr[i][j];
                            arr[i][j+1] = 0;
                        }
                    }
                }
                break;
            case 'U':
                for(int i = 0; i<n; i++) {
                    for(int j = 0; j < n-1; j++) {
                        if(arr[j][i] == arr[j+1][i]) {
                            arr[j][i] += arr[j][i];
                            arr[j+1][i] = 0;
                        }
                    }
                }
                break;
            case 'D':
                for(int i = 0; i<n; i++) {
                    for(int j = n-1; j > 0; j--){
                        if(arr[j][i] == arr[j-1][i]) {
                            arr[j][i] += arr[j][i];
                            arr[j-1][i] = 0;
                        }
                    }
                }
                break;

        }
    }

    public static void do2048(int[][] arr, char cmd) {
        switch(cmd){
            case 'R':
                for(int i = 0; i < n; i++) {
                    int[] temp = new int[n];
                    int tempRow = 0;
                    for(int j = n-1; j >= 0; j--) {
                        if(arr[i][j] != 0) {
                            temp[tempRow++] = arr[i][j];
                        }
                    }
                    // System.out.println("temp is ");
                    for(int j = 0; j < n; j++) {
                        // System.out.print(temp[j]+" ");
                        arr[i][n-1-j] = temp[j];
                    }
                    // System.out.println();
                }
                break;
            case 'L':
                for(int i = 0; i < n; i++) {
                    int[] temp = new int[n];
                    int tempRow = 0;
                    for(int j = 0; j < n; j++) {
                        if(arr[i][j] != 0) {
                            temp[tempRow++] = arr[i][j];
                        }
                    }
                    // System.out.println("temp is ");
                    for(int j = 0; j < n; j++) {
                        // System.out.print(temp[j]+" ");
                        arr[i][j] = temp[j];
                    }
                    // System.out.println();
                }
                break;
            case 'U':
                for(int i = 0; i < n; i++) {
                    int[] temp = new int[n];
                    int tempRow = 0;
                    for(int j = 0; j < n; j++) {
                        if(arr[j][i] != 0) {
                            temp[tempRow++] = arr[j][i];
                        }
                    }
                    // System.out.println("temp is ");
                    for(int j = 0; j < n; j++) {
                        // System.out.print(temp[j]+" ");
                        arr[j][i] = temp[j];
                    }
                    // System.out.println();

                }
                break;
            case 'D':
                for(int i = 0; i < n; i++) {
                    int[] temp = new int[n];
                    int tempRow = 0;
                    for(int j = n-1; j >= 0; j--) {
                        if(arr[j][i] != 0) {
                            temp[tempRow++] = arr[j][i];
                        }
                    }

                    // System.out.println("temp is ");
                    for(int j = 0; j < n; j++) {
                        // System.out.print(temp[j]+" ");
                        arr[n-1-j][i] = temp[j];
                    }
                    // System.out.println();
                }
                break;

        }
    }

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cmd = br.readLine().charAt(0);

        do2048(arr, cmd);
        // for(int i = 0; i< n; i++) {
        //     for(int j = 0; j < n; j++) {
        //         System.out.print(arr[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();
        sum2048(arr, cmd);
        do2048(arr, cmd);

        for(int i = 0; i< n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

    }
}