package CodeTree.IntermediateLow.Simulation.first;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Best33 {
    private static int n;
    private static int[][] coin = new int[20][20];

    public static int countCoin(int row, int col) {
        int sum = 0;

        for(int i = row; i < row + 3; i++) {
            for(int j = col; j < col + 3; j++) {
                sum += coin[i][j];
            }
        }

        return sum;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                coin[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxCoin = 0;

        for(int i = 0; i < n-2; i++) {
            for(int j = 0; j < n-2; j++) {
                // System.out.println("i is " + i + " j is " + j);
                maxCoin = Math.max(maxCoin, countCoin(i,j));
            }
        }

        System.out.println(maxCoin);
    }
}