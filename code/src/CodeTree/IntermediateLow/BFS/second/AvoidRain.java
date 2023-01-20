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

public class AvoidRain {
    private static int n, h, m;
    private static int[][] arr = new int[100][100];
    private static int[][] step = new int[100][100];
    private static boolean[][] visited = new boolean[100][100];
    private static Queue<Pair> q = new LinkedList<>();
    private static ArrayList<Pair> man = new ArrayList<>();
    private static ArrayList<Integer> ans = new ArrayList<>();

    public static boolean isRange(int x, int y){
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static boolean canGo(int x, int y){
        if(!isRange(x, y)) return false;
        if(visited[x][y] || arr[x][y] == 1) return false;
        return true;
    }

    public static void init(){

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                visited[i][j] = false;
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                step[i][j] = 0;
            }
        }

        while(!q.isEmpty()){
            q.poll();
        }
    }

    public static void push(int x, int y, int s){
        Pair pair = new Pair(x, y);
        q.add(pair);
        visited[x][y] = true;
        step[x][y] = s;
    }

    public static void avoidRain(int x, int y){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        while(!q.isEmpty()){
            Pair cur = q.poll();
            // System.out.println("curX: " + cur.x + " curY: " + cur.y + " step: " + step[cur.x][cur.y]);
            for(int i = 0; i < 4; i++){
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                // System.out.println("nextX: " + nextX + "nextY: " + nextY);
                if(canGo(nextX, nextY)){
                    if(arr[nextX][nextY] == 3) {
                        ans.add(step[cur.x][cur.y] + 1);
                        // System.out.println("\n\n");
                        return;
                    }
                    push(nextX, nextY, step[cur.x][cur.y] + 1);
                }
            }
        }
        ans.add(-1);
        // System.out.println("\n\n");
    }

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 2){
                    Pair pair = new Pair(i, j);
                    man.add(pair);
                }
                step[i][j] = 0;
            }
        }

        for(int i = 0; i < h; i++){
            Pair pair = new Pair(man.get(i).x, man.get(i).y);
            init();
            push(pair.x, pair.y, 0);
            avoidRain(pair.x, pair.y);
        }

        int k = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(k == h){
                    System.out.print(0 + " ");
                }else if(i == man.get(k).x && j == man.get(k).y){
                    System.out.print(ans.get(k) + " ");
                    k++;
                }else{
                    System.out.print(0+ " ");
                }
            }
            System.out.println();
        }
    }
}