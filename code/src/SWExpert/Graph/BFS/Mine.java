package SWExpert.Graph.BFS;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
import java.io.FileInputStream;

class Pair implements Comparable<Pair>{
    int x, y;
    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Pair p){
        if(this.x == p.x){
            return this.y - p.y;
        }else{
            return this.x - p.x;
        }
    }
}

public class Mine {
    private static int n, ans;
    private static char[][] map;
    private static TreeSet<Pair> check;
//    private static TreeSet<Integer> zeroX;
//    private static TreeSet<Integer> zeroY;
    private static int[][] zero;
    private static boolean[][] visited;

    private static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    public static boolean isRange(int x, int y){
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static boolean canGo(int x, int y){
        if(!isRange(x, y)) return false;
        if(visited[x][y] || map[x][y] == '*') return false;
        return true;
    }

    public static void roundMine(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(map[i][j] == '.'){
                    int cnt = 0;
                    for(int idx = 0; idx < 8; idx++) {
                        int nextX = i + dx[idx];
                        int nextY = j + dy[idx];
                        if (isRange(nextX, nextY) && map[nextX][nextY] == '*') {
                            cnt++;
                        }
                    }
                    zero[i][j] = cnt;
                }
            }
        }
    }

    public static void zeroClick(int x, int y){
        Queue<Pair> q = new LinkedList<>();

        q.add(new Pair(x, y));
        visited[x][y] = true;

        while(!q.isEmpty()){
            Pair cur = q.poll();
            for(int idx = 0; idx < 8; idx++){
                int nextX = cur.x + dx[idx];
                int nextY = cur.y + dy[idx];
                if(canGo(nextX, nextY)){
                    if(zero[nextX][nextY] == 0){
                        q.add(new Pair(nextX, nextY));
                    }
                    visited[nextX][nextY] = true;
                }
            }
        }

    }

    public static void main(String args[]) throws Exception {
        StringBuilder sb = new StringBuilder();
        System.setIn(new FileInputStream("SW_Testcase/BFS/mine.txt"));
//        System.setIn(new FileInputStream("SW_Testcase/BFS/mine_test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++){
            n = Integer.parseInt(br.readLine());
            map = new char[n][n];
            zero = new int[n][n];
            visited = new boolean[n][n];
            ans = 0;

            for(int i = 0; i < n; i++) {
                map[i] = br.readLine().toCharArray();
            }

            roundMine();

            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(zero[i][j] == 0 && map[i][j] != '*' && !visited[i][j]){
                        zeroClick(i,j);
//                        System.out.println(i + " " + j);
                        ans++;
                    }
                }
            }
//            System.out.println(ans);
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(zero[i][j] > 0 && map[i][j] != '*' && !visited[i][j]){
                        ans++;
                    }
                }
            }

            sb.append("#" + test_case + " " + ans + "\n");
        }
        System.out.println(sb);


    }
}
