package CodeTree.IntermediateLow.Backtracking.fourth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class MaxSum {
    private static int n, max = -1;
    private static int[][] arr = new int[10][10];
    private static boolean[][] visited = new boolean[10][10];

    public static int calc(){
        int sum = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(visited[i][j]) {
                    sum += arr[i][j];
                }
            }
        }

        return sum;
    }

    public static void findMaxSum(int cnt){
        if(cnt == n) {
            max = Math.max(max, calc());
            return;
        }

        for(int row = 0; row < n; row++) {
            for(int col = 0; col < n; col++) {
                boolean check = false;
                // 각 행과 열에 한번씩만 오는 것을 체크
                for(int j = 0; j < n; j++) {
                    if(visited[row][j]){
                        check = true;
                    }
                }
                for(int i = 0; i < n; i++) {
                    if(visited[i][col]){
                        check = true;
                    }
                }
                if(check){
                    continue;
                }

                visited[row][col] = true;
                findMaxSum(cnt+1);
                visited[row][col] = false;
            }
        }
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

        findMaxSum(0);
        System.out.println(max);
    }
}