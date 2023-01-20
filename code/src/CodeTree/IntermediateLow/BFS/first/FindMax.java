package CodeTree.IntermediateLow.BFS.first;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

class Pair{
    int x, y;
    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class FindMax {
    private static int n, k, m, ans = -1;
    private static int[][] arr = new int[100][100];
    //arrayList로 해도 좋았을 듯?
    private static Pair[] start = new Pair[10000];
    private static boolean[][] visited = new boolean[100][100];

    private static ArrayList<Pair>selectedStones = new ArrayList<>();
    private static ArrayList<Pair>stonePos = new ArrayList<>();
    public static ArrayList<Pair> sPos = new ArrayList<>();

    public static Queue<Pair> q = new LinkedList<>();

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static boolean canGo(int x, int y) {
        return inRange(x, y) && arr[x][y] == 0 && !visited[x][y];
    }

    public static void BFS(){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        while(!q.isEmpty()){
            Pair cur = q.poll();
            for(int i = 0; i < 4; i++){
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                if(canGo(nextX, nextY)){
                    visited[nextX][nextY] = true;
                    q.add(new Pair(nextX, nextY));
                }
            }
        }
    }

    public static int calc(){
        int count = 0;

        for(int i = 0; i <(int)selectedStones.size(); i++){
            int x = selectedStones.get(i).x;
            int y = selectedStones.get(i).y;
            arr[x][y] = 0;
        }

        for(int i = 0; i < k; i++) {
            q.add(sPos.get(i));
            visited[sPos.get(i).x][sPos.get(i).y] = true;
        }
        BFS();

        // 초기화
        for(int i = 0; i <(int)selectedStones.size(); i++){
            int x = selectedStones.get(i).x;
            int y = selectedStones.get(i).y;
            arr[x][y] = 1;
        }

        for(int i =0; i< n; i++) {
            for(int j = 0; j < n; j++){
                if(visited[i][j]){
                    count++;
                }
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                visited[i][j] = false;
            }
        }
        return count;

    }

    public static void findMax(int idx, int cnt){
        if(idx == (int) stonePos.size()){
            if(cnt == m){
                ans = Math.max(ans, calc());
            }
            return;
        }

        selectedStones.add(stonePos.get(idx));
        findMax(idx + 1, cnt + 1);
        selectedStones.remove(selectedStones.size() - 1);

        findMax(idx + 1, cnt);

    }

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 1){
                    stonePos.add(new Pair(i, j));
                }
            }
        }

        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) -1;
            int col = Integer.parseInt(st.nextToken()) -1;
            sPos.add(new Pair(row, col));
        }


        // 완탐으로 돌멩이갯수만큼 치우고 BFS로 가능한 곳 세기
        findMax(0, 0);
        System.out.println(ans);
    }
}