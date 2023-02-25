package CodeTree.IntermediateLow.Backtracking.first;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

class Pair{
    int x, y;
    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class StrongExplosion {
    private static int MAXSIZE = 20;
    private static int n, count = 0;
    private static int[][] newBomb = new int[MAXSIZE][MAXSIZE];
    private static ArrayList<Pair> bombPos = new ArrayList<>();

    public static boolean isRange(int x, int y) {
        return 0<= x && x < n && 0 <= y && y < n;
    }

    public static int countBomb(){
        int[][] area = new int[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(newBomb[i][j] == 1) {
                    area[i][j] = 1;
                    if(isRange(i-2, j)){
                        area[i-2][j]= 1;
                    }
                    if(isRange(i-1, j)){
                        area[i-1][j]= 1;
                    }
                    if(isRange(i+1, j)){
                        area[i+1][j]= 1;
                    }
                    if(isRange(i+2, j)){
                        area[i+2][j]= 1;
                    }
                }
                if(newBomb[i][j] == 2) {
                    area[i][j] = 1;
                    if(isRange(i-1, j)){
                        area[i-1][j]= 1;
                    }
                    if(isRange(i, j+1)){
                        area[i][j+1]= 1;
                    }
                    if(isRange(i+1, j)){
                        area[i+1][j]= 1;
                    }
                    if(isRange(i, j-1)){
                        area[i][j-1]= 1;
                    }
                }
                if(newBomb[i][j] == 3) {
                    area[i][j] = 1;
                    if(isRange(i-1, j-1)){
                        area[i-1][j-1]= 1;
                    }
                    if(isRange(i-1, j+1)){
                        area[i-1][j+1]= 1;
                    }
                    if(isRange(i+1, j+1)){
                        area[i+1][j+1]= 1;
                    }
                    if(isRange(i+1, j-1)){
                        area[i+1][j-1]= 1;
                    }
                }
            }
        }
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(area[i][j] == 1) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public static void bomb(int num){
        if(num == (int)bombPos.size()){
            count = Math.max(count, countBomb());
            return;
        }

        for(int i = 1; i <= 3; i++) {
            int x = bombPos.get(num).x;
            int y = bombPos.get(num).y;

            newBomb[x][y] = i;
            bomb(num+1);
            newBomb[x][y] = 0;

        }
    }

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                int n = Integer.parseInt(st.nextToken());
                if(n > 0){
                    bombPos.add(new Pair(i,j));
                }

            }
        }

        bomb(0);

        System.out.println(count);

    }
}