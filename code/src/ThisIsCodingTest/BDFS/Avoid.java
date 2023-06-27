package ThisIsCodingTest.BDFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Avoid {
    private static int N;
    private static ArrayList<Pos> teachers = new ArrayList<>();
    private static char[][] road;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static boolean canGo(int x, int y) {
        if(x < 0 || x >= N || y < 0 || y >= N) return false;
        if(road[x][y] == 'O') return false;

        return true;
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
