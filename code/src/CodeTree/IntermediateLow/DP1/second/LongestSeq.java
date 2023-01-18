package CodeTree.IntermediateLow.DP1.second;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Collections;

class Pair implements Comparable<Pair>{
    int x, y, num;

    public Pair(int x, int y, int num){
        this.x = x;
        this.y = y;
        this.num = num;
    }

    @Override
    public int compareTo(Pair pair){
        if(pair.num < num){
            return 1;
        }else if(pair.num == num){
            return 0;
        }else{
            return -1;
        }
    }
}

public class LongestSeq {
    private static int n;
    private static int[][] arr = new int[500][500];
    private static int[][] dp = new int[500][500];
    private static ArrayList<Pair> arrayList = new ArrayList<>();

    public static boolean isRange(int x, int y){
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                Pair pair = new Pair(i, j, arr[i][j]);
                arrayList.add(pair);
            }
        }

        Collections.sort(arrayList);

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        for(int i = 0; i < (int)arrayList.size(); i++){
            int curX = arrayList.get(i).x;
            int curY = arrayList.get(i).y;
            int curNum = arrayList.get(i).num;

            for(int idx = 0; idx < 4; idx++){
                int nextX = curX + dx[idx];
                int nextY = curY + dy[idx];

                if(isRange(nextX, nextY) && curNum < arr[nextX][nextY]){
                    dp[nextX][nextY] = Math.max(dp[nextX][nextY], dp[curX][curY] + 1);
                }
            }
        }

        int max= -1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                max = Math.max(dp[i][j], max);
            }
        }

        System.out.println(max+1);
    }
}