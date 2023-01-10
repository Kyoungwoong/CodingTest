package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

public class Pre1 {
    private static int tc, row, col, ans = 0, maxRow = 20, maxCol = 20, idx = 4;
    private static boolean add;
    private static char[][] souvenir = new char[maxRow][maxCol];
    private static int[][] step = new int[maxRow][maxCol];
    private static boolean[][] visited = new boolean[maxRow][maxCol];
    private static Queue<Pair> q = new LinkedList<>();
    private static ArrayList<Character> items = new ArrayList<>();

    public static boolean isRange(int x, int y){
        return 0 <= x && x < row && 0 <= y && y < col;
    }

    public static boolean canGo(int x, int y){
        if(!isRange(x, y)) return false;
        if(visited[x][y]) return false;

        char item = souvenir[x][y];
        for(int i = 0; i < (int) items.size(); i++){
            if(item == items.get(i)) {
                return false;
            }
        }

        return true;
    }
    public static void init(){
        items.clear();
        ans = 0;
        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                visited[r][c] = false;
                step[r][c] = 0;
            }
        }
    }

    public static void push(int x, int y, char item, int s){
        Pair pair = new Pair(x, y);
        q.add(pair);
        items.add(item);
        visited[x][y] = true;
        step[x][y] = s;

    }

    public static void findMaxSouvenir(){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        while(!q.isEmpty()){
            Pair cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;

            for(int i = 0; i < idx; i++){
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];
                if(canGo(nextX, nextY)){
                    push(nextX, nextY, souvenir[nextX][nextY], step[curX][curY] + 1);
                    add = true;
                }

                if(add){
                    items.remove(items.size() -1);
                    add = false;
                }

            }
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // testcase 수 받기
        tc = Integer.parseInt(br.readLine());

        for(int t = 0; t < tc; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 행과 열 받기
            row = Integer.parseInt(st.nextToken());
            col = Integer.parseInt(st.nextToken());

            // 기념품 초기화
            for(int r = 0; r < row; r++){
                String str = br.readLine();
                for(int c = 0; c < col; c++){
                    souvenir[r][c] = str.charAt(c);
                }
            }
            // visited, step, items 초기화
            init();
            // 첫 시작점 큐에 넣기
            push(0, 0, souvenir[0][0], 1);
            // 최대 기념품 수 찾기
            findMaxSouvenir();

            int max = -1;
            for(int i = 0; i < row; i++){
                for(int j = 0; j < col; j++){
                    max = Math.max(max, step[i][j]);
                    System.out.print(step[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();

            System.out.println("#" + (t+1) + " " + max);
        }
    }

}
