package ThisIsCodingTest.BDFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class Pair{
    int x, y;
    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Ice {
    public static int N, M, cnt = 0;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static int[][] frame = new int[1000][1000];
    public static boolean[][] visited = new boolean[1000][1000];

    // bfs
    public static Queue<Pair> q = new LinkedList<>();
    // dfs
    public static Stack<Pair> s = new Stack<>();

    public static boolean isRange(int x, int y){
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static boolean canGo(int x, int y){
        if(!isRange(x, y)) return false;
        if(visited[x][y] || frame[x][y] == 1) return false;

        return true;
    }

    public static void bfs(int x, int y){
        q.add(new Pair(x, y));

        while(!q.isEmpty()){
            Pair cur = q.poll();
            for(int idx = 0; idx < 4; idx++){
                int nextX = cur.x + dx[idx];
                int nextY = cur.y + dy[idx];
                if(canGo(nextX, nextY)){
                    q.add(new Pair(nextX, nextY));
                    visited[nextX][nextY] = true;
                }
            }
        }
    }

    public static void dfs(int x, int y){
        visited[x][y] = true;
        s.add(new Pair(x, y));
        for(int idx = 0; idx < 4; idx++) {
            int nx = x + dx[idx];
            int ny = y + dy[idx];
            if (canGo(nx, ny)) {
                dfs(nx, ny);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // N * M 얼음 틀
        // 구멍 0 칸막이 1
        // 구멍이 뚫려있는 부분은 하나로 간주
        // 총 아이스크림 갯수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            String line = br.readLine();
            for(int j = 0; j < M; j++){
                frame[i][j] = line.charAt(j) - '0';
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                // 이전코드
//                if(!visited[i][j] && frame[i][j] == 0){
//                    // bfs
////                    bfs(i, j);
//                    // dfs
//                    dfs(i, j);
//                    cnt++;
//                }
                // 최적화 코드
                if (canGo(i, j)) {
//                    bfs(i, j);
                    dfs(i, j);
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}

/*
4 5
00110
00011
11111
00000
--- 3

15 14
00000111100000
11111101111110
11011101101110
11011101100000
11011111111111
11011111111100
11000000011111
01111111111111
00000000011111
01111111111000
00011111111000
00000001111000
11111111110011
11100011111111
11100011111111
--- 8
 */
