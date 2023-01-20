package CodeTree.IntermediateLow.BFS.first;

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

public class MoveMax{
    private static int n, k, curValue;
    private static Pair cur;
    private static int[][] arr = new int[100][100];
    private static boolean[][] visited = new boolean[100][100];
    private static Queue<Pair> q = new LinkedList<>();

    public static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static boolean canGo(int x, int y){
        if(!isRange(x, y)) return false;
        if(visited[x][y] || curValue <= arr[x][y]) return false;
        return true;
    }

    public static Pair findMax(){
        int max = -1, maxX = -1, maxY = -1;
        //          북  동  남  서
        int[] dx = {-1, 0, 1, 0};
        int[] dy = { 0, 1, 0, -1};

        while(!q.isEmpty()){
            Pair temp = q.poll();

            for(int idx = 0; idx < 4; idx++){
                int nextX = temp.x + dx[idx];
                int nextY = temp.y + dy[idx];
                if(canGo(nextX, nextY)){
                    Pair next = new Pair(nextX, nextY);
                    push(next);
                    if(max < arr[nextX][nextY]){
                        max = arr[nextX][nextY];
                        maxX = nextX;
                        maxY = nextY;
                    }else if(max == arr[nextX][nextY]){
                        if(maxX > nextX){
                            maxX = nextX;
                            maxY = nextY;
                        }else if(maxX == nextX){
                            if(maxY > nextY){
                                maxY = nextY;
                                maxX = nextX;
                            }
                        }
                    }
                }
            }
        }
        // System.out.println("maxX: " + maxX + " " + " maxY: " + maxY + " maxValue: " + arr[maxX][maxY]);
        if(maxX == -1){
            maxX = cur.x;
            maxY = cur.y;
        }
        Pair maxPair = new Pair(maxX, maxY);
        return maxPair;
    }

    public static void push(Pair pair){
        q.add(pair);
        visited[pair.x][pair.y] = true;
    }

    public static void init(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                visited[i][j] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(st.nextToken()) -1;
        int startY = Integer.parseInt(st.nextToken()) -1;
        cur = new Pair(startX, startY);


        for(int i = 0; i < k; i++){
            curValue = arr[cur.x][cur.y];
            // System.out.println("curX: " + cur.x + " " + " curY: " + cur.y + " value: " + curValue);
            init();
            push(cur);
            cur = findMax();
        }

        System.out.println((cur.x + 1) + " " + (cur.y + 1));
    }
}