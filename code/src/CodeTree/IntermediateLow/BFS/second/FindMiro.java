package CodeTree.IntermediateLow.BFS.second;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

//class Pair{
//    int x, y;
//    public Pair(int x, int y){
//        this.x = x;
//        this.y = y;
//    }
//}

public class FindMiro {
    private static int n, m;
    private static int[][] step = new int[100][100];
    private static int[][] arr = new int[100][100];
    private static boolean[][] visited = new boolean[100][100];
    private static Queue<Pair>q = new LinkedList<>();

    public static boolean isRange(int x, int y){
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public static boolean canGo(int x, int y){
        if(!isRange(x, y)) return false;
        if(visited[x][y] || arr[x][y] == 0) return false;
        return true;
    }

    public static void findMiro(){
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        while(!q.isEmpty()){
            Pair cur = q.poll();
            for(int i = 0; i < 4; i++){
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                if(canGo(nextX, nextY)){
                    push(nextX, nextY, step[cur.x][cur.y] + 1);
                }
            }
        }
        if(visited[n - 1][m - 1])
            System.out.println(step[n-1][m-1]);
        else{
            System.out.println(-1);
        }

    }

    public static void push(int x, int y, int s){
        Pair pair = new Pair(x, y);
        q.add(pair);
        visited[pair.x][pair.y] = true;
        step[x][y] = s;
    }

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        push(0, 0, 0);

        findMiro();

    }
}