package SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;


public class Pre1_2 {
    private static int tc, row, col, maxRow = 20, maxCol = 20, ans = 0, idx = 4;
    private static char[][] souvenir = new char[maxRow][maxCol];
    private static boolean[][] visited = new boolean[maxRow][maxCol];
    private static ArrayList<Character> items = new ArrayList<>();
//    private static boolean[] items2 = new boolean[26];

    public static boolean isRange(int x, int y){
        return 0 <= x && x < row && 0 <= y && y < col;
    }
    public static boolean canGo(int x, int y){
        if(!isRange(x, y)) return false;
        if(visited[x][y]) return false;

        char item = souvenir[x][y];
        for(int i = 0; i < (int)items.size(); i++){
            if(item == items.get(i)){
                return false;
            }
        }

        return true;
    }

    public static void findMaxSouv(int x, int y){
        ans = Math.max(ans, (int)items.size());

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        for(int i = 0; i < idx; i++){
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if(canGo(nextX, nextY)){
                visited[nextX][nextY] = true;
                items.add(souvenir[nextX][nextY]);

                findMaxSouv(nextX, nextY);

                visited[nextX][nextY] = false;
                items.remove(items.size()-1);
            }
        }
    }

    public static void init(){
        items.clear();
        ans = 0;

        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                visited[r][c] = false;
            }
        }

        visited[0][0] = true;
        items.add(souvenir[0][0]);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // testcase 수 받기
        tc = Integer.parseInt(br.readLine());

        for(int t = 0; t < tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 행과 열 받기
            row = Integer.parseInt(st.nextToken());
            col = Integer.parseInt(st.nextToken());

            // 기념품 초기화
            for (int r = 0; r < row; r++) {
                String str = br.readLine();
                for (int c = 0; c < col; c++) {
                    souvenir[r][c] = str.charAt(c);
                }
            }

            // visited, items 초기화
            init();

            findMaxSouv(0, 0);
            System.out.println("#" + (t+1) + " " + ans);
        }
    }
}
