package ThisIsCodingTest.BDFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Year;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Avoid {
    static class Pos{
        int x, y;
        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    private static int N;
    private static ArrayList<Pos> teachers = new ArrayList<>();
    private static char[][] road;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int COUNT = 0;

    public static boolean canGo(int x, int y) {
        if(isRange(x, y)) return false;
        if(road[x][y] == 'O') return false;

        return true;
    }

    private static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }

    public static void dfs(int count) {
        if (count == 3) {
            char[][] temp = road;
            for (int i = 0; i < teachers.size(); i++) {
                int x = teachers.get(i).x;
                int y = teachers.get(i).y;
                for (int idx = 0; idx < 4; idx++) {
                    int nextX = x + dx[idx];
                    int nextY = y + dy[idx];
                    while (canGo(nextX, nextY)) {
                        if (temp[nextX][nextY] == 'S') {
                            return;
                        }
                        temp[nextX][nextY] = 'T';

                        nextX = nextX + dx[idx];
                        nextY = nextY + dy[idx];
                    }
                }
                System.out.println("YES");
                System.exit(0);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (road[i][j] == 'X') {
                    road[i][j] = 'O';
                    dfs(count + 1);
                    road[i][j] = 'X';
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        prev();
        oct30();
    }

    private static void oct30() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        road = new char[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                road[i][j] = st.nextToken().charAt(0);
                if (road[i][j] == 'T') {
                    teachers.add(new Pos(i, j));
                }
            }
        }

        barricade(0);

        System.out.println("NO");
    }

    private static void barricade(int cnt) {
        if (cnt == 3) {
            boolean flag = bfs();
            if (flag) {
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (road[i][j] == 'X') {
                    road[i][j] = 'O';
                    barricade(cnt + 1);
                    road[i][j] = 'X';
                }
            }
        }
    }

    private static boolean bfs() {
        boolean[][] visited = new boolean[N][N];
        Queue<Pos> q = new LinkedList<>();

        for (Pos teacher : teachers) {
            visited[teacher.x][teacher.y] = true;
            q.add(teacher);

            // 상하좌우 방향으로 탐색
            int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
            int[] dy = {0, 0, -1, 1};

            while (!q.isEmpty()) {
                Pos cur = q.poll();

                // 각 방향에 대해 탐색
                for (int dir = 0; dir < 4; dir++) {
                    int nextX = cur.x;
                    int nextY = cur.y;

                    // 현재 방향으로 쭉 이동
                    while (!isRange(nextX, nextY)) {
                        if (road[nextX][nextY] == 'O') {
                            break;
                        } else if (road[nextX][nextY] == 'S') {
                            return false;
                        }

                        // 다음 칸으로 이동
                        nextX += dx[dir];
                        nextY += dy[dir];
                    }
                }
            }
        }
        return true; // 모든 선생님 탐색 후 아무 문제 없으면 true 반환
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        road = new char[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                road[i][j] = st.nextToken().charAt(0);
                if (road[i][j] == 'T') {
                    teachers.add(new Pos(i, j));
                }
            }
        }

        dfs(0);
        System.out.println("NO");
    }
}

/*
5
X S X X T
T X S X X
X X X X X
X T X X X
X X T X X
--- YES

4
S S S T
X X X X
X X X X
T T T X
--- NO
 */
