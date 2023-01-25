package CodeTree.IntermediateLow.DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class FindCity {
    private static int n, people;
    private static int[][] arr = new int[25][25];
    private static boolean[][] visited = new boolean[25][25];
    private static ArrayList<Integer> city = new ArrayList<>();

    public static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static boolean canGo(int x, int y) {

        if(!isRange(x, y)){
            return false;
        }

        if(arr[x][y] == 0 || visited[x][y]) {
            return false;
        }

        return true;
    }

    public static void findCity(int curX, int curY){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        visited[curX][curY] = true;

        for(int i = 0; i < 4; i++) {
            int nextX = curX + dx[i];
            int nextY = curY + dy[i];
            if(canGo(nextX, nextY)){
                people++;
                findCity(nextX, nextY);
            }
        }

    }

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(canGo(i, j)){
                    people = 1;
                    findCity(i, j);
                    city.add(people);
                }
            }
        }

        System.out.println(city.size());
        Collections.sort(city);
        for(int i = 0; i <(int)city.size(); i++) {
            System.out.println(city.get(i));
        }
    }
}