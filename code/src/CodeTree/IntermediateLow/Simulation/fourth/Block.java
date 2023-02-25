package CodeTree.IntermediateLow.Simulation.fourth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Block {
    private static int MAXSIZE = 100;
    private static int n, m, k;
    private static int[][] arr = new int[MAXSIZE][MAXSIZE];

    public static void fallBlock(int m, int k){
        int i;
        boolean check = false;
        for(i = 1; i < n; i++) {
            for(int j = k; j < k + m; j++) {
                if(arr[i][j] == 1) {
                    check = true;
                    break;
                }
            }
            if(check) {
                break;
            }
        }

        for(int j = k; j < k + m; j++) {
            arr[i-1][j] = 1;
        }


    }

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j <n ; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        fallBlock(m, k-1);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j <n ; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}