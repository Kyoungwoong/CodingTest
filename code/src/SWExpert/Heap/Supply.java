package SWExpert.Heap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos{
    int x, y;
    public Pos(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Supply {
    private static int c, ans;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[][] step;
    private static Queue<Pos> q = new LinkedList<>();

    public static void init(){
        q.clear();

        for(int i = 0; i < c; i++){
            for(int j = 0; j < c; j++){
                step[i][j] = Integer.MAX_VALUE;
            }
        }

        step[0][0] = 0;
    }

    public static void push(int x, int y){
        q.add(new Pos(x, y));
        visited[x][y] = true;
    }

    public static void findMinValue(){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        while(!q.isEmpty()){
            Pos pos = q.poll();
            if(pos.x == c-1 && pos.y == c-1){
                ans = Math.min(ans, step[c-1][c-1]);
            }
            if(ans <= step[pos.x][pos.y]){
                continue;
            }

            for(int idx = 0; idx < 4; idx++){
                int nextX = pos.x + dx[idx];
                int nextY = pos.y + dy[idx];
                if(canGo(nextX, nextY)){
                    if(step[nextX][nextY] > step[pos.x][pos.y] + map[nextX][nextY]){
                        push(nextX, nextY);
                        step[nextX][nextY] = step[pos.x][pos.y] + map[nextX][nextY];
                    }

                }
            }
        }

    }

    public static boolean isRange(int x, int y){
        return 0 <= x && x < c && 0 <= y && y < c;
    }

    public static boolean canGo(int x, int y){
        if(!isRange(x, y)) return false;
//        if(visited[x][y]) return false;

        return true;
    }

    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("SW_Testcase/Heap/supply.txt"));

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {
            c = Integer.parseInt(br.readLine());
            map = new int[c][c];
            visited = new boolean[c][c];
            step = new int[c][c];
            ans = Integer.MAX_VALUE;

            for(int i = 0; i < c; i++){
                String row = br.readLine();
                for(int j = 0; j < c; j++){
                    map[i][j] = row.charAt(j) -'0';
                }
            }

            init();
            push(0,0);

            findMinValue();

            sb.append("#" + test_case + " " + ans +"\n");
        }
        System.out.println(sb);
    }
}
