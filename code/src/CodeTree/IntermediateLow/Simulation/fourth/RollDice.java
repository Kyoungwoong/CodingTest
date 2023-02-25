package CodeTree.IntermediateLow.Simulation.fourth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Scanner;

public class RollDice {
    private static int MAXSIZE = 100;
    private static int n, m, r, c;
    private static int[][] arr = new int[MAXSIZE][MAXSIZE];
    private static char cmd;

    public static boolean isRange(int x, int y){
        return 0<=x && x<n && 0<=y && y<n;
    }

    public static int rollDice(int row, int col, int up, int front, int right){
        int temp, sum = 0;
        Scanner sc = new Scanner(System.in);

        // 주사위 m번 실행
        for(int i = 0; i<m; i++){
            char cmd = sc.next().charAt(0);
            arr[row][col] = 7-up;
            sum += arr[row][col];
            switch(cmd){
                case 'U':
                    if(!isRange(row-1, col)) continue;
                    row -= 1;
                    temp = up;
                    up = front;
                    front = 7 - temp;
                    break;

                case 'D':
                    if(!isRange(row+1, col)) continue;
                    row += 1;
                    temp = front;
                    front = up;
                    up = 7-temp;
                    break;

                case 'L':
                    if(!isRange(row, col-1)) continue;
                    col -= 1;
                    temp = up;
                    up = right;
                    right = 7-temp;
                    break;

                case 'R':
                    if(!isRange(row, col+1)) continue;
                    col += 1;
                    temp = right;
                    right = up;
                    up = 7-temp;
                    break;
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        // Scanner sc = new Scanner(System.in);

        System.out.println(rollDice(r-1, c-1, 1,2,3));
    }
}