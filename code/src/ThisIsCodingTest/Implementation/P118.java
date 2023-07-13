package ThisIsCodingTest.Implementation;

import java.awt.image.BandedSampleModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    int x, y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class P118 {
    private static int N, M, d, A, B, ans = 0;
    private static int[][] map;
    private static boolean[][] visited;
    private static Queue<Node> q = new LinkedList<>();
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static boolean isRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static boolean canGo(int x, int y) {
        if (!isRange(x, y)) return false;
        if (visited[x][y] || map[x][y] == 1) return false;

        return true;
    }

    public static void init() {
        q.add(new Node(A, B));
        visited[A][B] = true;
    }
    public static void visit(int dir) {
        Node cur = q.poll();
        for (int i = 0; i < 4; i++) {
            if (cur == null) {
                break;
            }


            int nextX = cur.x + dx[dir];
            int nextY = cur.y + dy[dir];
            if (canGo(nextX, nextY)) {
                q.add(new Node(nextX, nextY));
                visited[nextX][nextY] = true;
                visit(dir);
                ans++;
                System.out.println(nextX + " " + nextY);
            }
            dir = (dir + 3) % 4;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        init();
        visit(d);
        System.out.println("ans = " + ans);
    }
}
/*
4 4
1 1 0
1 1 1 1
1 0 0 1
1 1 0 1
1 1 1 1
--- 3
 */
