package CodeTree.IntermediateLow.Simulation.first;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class FindGold {
    private static int n, m;
    private static int[][] arr = new int[20][20];

    public static int getArea(int k) {
        return k*k + (k+1)*(k+1);
    }

    public static int findGold(int row, int col, int k) {
        int numOfGold = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(Math.abs(row - i) + Math.abs(col - j) <= k)
                    numOfGold += arr[i][j];
            }
        }
        return numOfGold;
    }

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int gold = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k <= 2*(n-1); k++) {
                    int numOfGold = findGold(i, j, k);
                    if(numOfGold * m >= getArea(k)){
                        gold = Math.max(gold, numOfGold);
                    }
                }

            }
        }

        System.out.println(gold);
    }
}