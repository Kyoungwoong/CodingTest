package CodeTree.IntermediateLow.BFS.second;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

//class Pair{
//    int x, y;
//    public Pair(int x, int y){
//        this.x = x;
//        this.y = y;
//    }
//}

public class Knight {
    private static int n, ans = 100000;
    private static Pair start, end;
    private static int[][] arr = new int[100][100];
    private static int[][] step = new int[100][100];
    private static boolean[][] visited = new boolean[100][100];
    private static Queue<Pair> q = new LinkedList<>();

    public static boolean isRange(int x, int y){
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static boolean canGo(int x, int y){
        if(!isRange(x, y)) return false;
        if(visited[x][y]) return false;
        return true;
    }

    public static void push(int x, int y, int s){
        Pair pair = new Pair(x, y);
        q.add(pair);
        visited[x][y] = true;
        step[x][y] = s;
    }

    public static void findPath(){
        int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

        while(!q.isEmpty()){
            Pair cur = q.poll();
            for(int i = 0; i < 8; i++){
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                if(canGo(nextX, nextY)){
                    push(nextX, nextY, step[cur.x][cur.y] + 1);
                }
            }
        }

        if(visited[end.x][end.y]){
            System.out.println(step[end.x][end.y]);
        }else{
            System.out.println(-1);
        }
    }

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = new Pair(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
        end = new Pair(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);

        push(start.x, start.y, 0);

        findPath();
    }
}