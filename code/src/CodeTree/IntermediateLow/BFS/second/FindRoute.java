package CodeTree.IntermediateLow.BFS.second;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Pair{
    int x, y;
    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class FindRoute {
    private static int n, k, ans = Integer.MAX_VALUE;
    private static Pair start, end;
    private static int[][] arr = new int[100][100];
    private static boolean[][] visited = new boolean[100][100];
    private static int[][] step = new int[100][100];
    private static Queue<Pair> q = new LinkedList<>();
    private static ArrayList<Pair> removedWall = new ArrayList<>();
    private static ArrayList<Pair> selectedWall = new ArrayList<>();

    public static boolean isRange(int x, int y){
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static boolean canGo(int x, int y){
        if(!isRange(x, y)) return false;
        if(visited[x][y] || arr[x][y] == 1) return false;

        return true;
    }

    public static int calc(){
        int[] dx = {-1, 0 , 1, 0};
        int[] dy = {0, 1, 0, -1};

        for(int i = 0; i < (int)selectedWall.size(); i++){
            arr[selectedWall.get(i).x][selectedWall.get(i).y] = 0;
        }

        // for(int i = 0; i < n; i++){
        //     for(int j = 0; j < n; j++){
        //         System.out.print(arr[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();

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

        for(int i = 0; i < (int)selectedWall.size(); i++){
            arr[selectedWall.get(i).x][selectedWall.get(i).y] = 1;
        }

        if(visited[end.x][end.y]){
            return step[end.x][end.y];
        }else{
            return Integer.MAX_VALUE;
        }
    }

    public static void init(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                step[i][j] = 0;
                visited[i][j] = false;
            }
        }

        push(start.x, start.y, 0);
    }

    public static void push(int x, int y, int s){
        Pair pair = new Pair(x, y);
        q.add(pair);
        visited[x][y] = true;
        step[x][y] = s;
    }

    public static void findRoute(int idx, int cnt){
        // if(idx == (int)removedWall.size()){
        //     if(cnt == k){
        //         init();
        //         ans = Math.min(ans, calc());
        //     }
        //     return;
        // }
        if(idx == (int)removedWall.size()){
            if(cnt == k){
                init();
                ans = Math.min(ans, calc());
            }
            return;
        }


        selectedWall.add(removedWall.get(idx));
        findRoute(idx + 1, cnt + 1);

        selectedWall.remove(selectedWall.size() - 1);
        findRoute(idx + 1, cnt);

    }

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 1){
                    removedWall.add(new Pair(i, j));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        start = new Pair(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
        st = new StringTokenizer(br.readLine());
        end = new Pair(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);

        findRoute(0, 0);

        if(ans == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(ans);
        }
    }
}