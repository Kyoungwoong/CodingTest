package ThisIsCodingTest.BDFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Miro {
    public static int N, M;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static boolean[][] visited = new boolean[200][200];
    public static int[][] miro = new int[200][200];
    public static int[][] step = new int[200][200];

    public static Queue<Pair> q = new LinkedList<>();

    public static boolean isRange(int x, int y){
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static boolean canGo(int x, int y){
        if(!isRange(x, y)) return false;
        if(visited[x][y] || miro[x][y] == 0) return false;

        return true;
    }

    public static void bfs(){
        while(!q.isEmpty()){
            Pair cur = q.poll();
            for(int idx = 0; idx < 4; idx++){
                int nextX = cur.x + dx[idx];
                int nextY = cur.y + dy[idx];
                if (canGo(nextX, nextY)) {
                    init(nextX, nextY, step[cur.x][cur.y] + 1);
                }
            }
        }
    }

    public static void init(int x, int y, int s){
        q.add(new Pair(x, y));
        visited[x][y] = true;
        if (step[x][y] != 0) {
            step[x][y] = Math.min(step[x][y], s);
        }else{
            step[x][y] = s;
        }
    }

    public static void main(String[] args) throws IOException {
        // N X M miro
        // 처음 위치 (1, 1) 출구 (N, M)
        // monster 0 path 1
        // 최소 칸의 갯수

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            String line = br.readLine();
            for(int j = 0; j < M; j++){
                miro[i][j] = line.charAt(j) - '0';
            }
        }

        init(0, 0, 1);
        bfs();
        System.out.println("step[N-1][M-1] = " + step[N-1][M-1]);
    }
}

/*
5 6
101010
111111
000001
111111
111111
--- 10
 */
