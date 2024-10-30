package ThisIsCodingTest.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Dir{
    int second;
    String dir;
    public Dir(int second, String dir){
        this.second = second;
        this.dir = dir;
    }
}

public class Snake {

    static class Pair{
        int x, y;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    private static int N, K, L;
    private static int[][] board = new int[100][100];
    private static ArrayList<Dir> arrayList = new ArrayList<>();
    private static Queue<Dir> rotate = new LinkedList<>();
    private static ArrayList<Pair> snake = new ArrayList<>();

    public static int dx[] = {0, 1, 0, -1};
    public static int dy[] = {1, 0, -1, 0};

    public static boolean canGo(int x, int y){
        if(x == N || y == N || x < 0 || y < 0) return false;
        if(board[x][y] == 2) return false;

        return true;
    }

    public static int game(){
        int time = 0;
        int dir = 0;
        int x = 0, y = 0, l = 0;
        board[x][y] = 2;
        int nextX = x + dx[dir];
        int nextY = y + dy[dir];
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x, y));

        while(canGo(nextX, nextY)){
            if(board[nextX][nextY] != 1){
                Pair tail = q.poll();
                board[tail.x][tail.y] = 0;
            }
            board[nextX][nextY] = 2;
            q.add(new Pair(nextX, nextY));
            time++;
            if(l < L && time == arrayList.get(l).second){
                if(arrayList.get(l).dir.equals("L")){
                    dir = (dir + 3) % 4;
                }else{
                    dir = (dir + 1) % 4;
                }
                l++;
            }
            nextX = nextX + dx[dir];
            nextY = nextY + dy[dir];
            System.out.println("time: " + time + " nextX nextY: " + nextX + " " +nextY);

        }

        return time;
    }

    public static void main(String[] args) throws IOException {
        prev();
//        oct30();
    }

    private static void oct30() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            board[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 1;
        }
        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            rotate.add(new Dir(Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        System.out.println(snakeGame());
    }

    private static int snakeGame() {
        int time = 0;
        Pair snake = new Pair(0, 0);
        int dir = 1;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        board[0][0] = 2;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0));
        int nextX = snake.x + dx[dir];
        int nextY = snake.y + dy[dir];

        while (canGo(nextX, nextY)) {
            System.out.println("time: " + time + " nextX nextY: " + nextX + " " +nextY);

            if(board[nextX][nextY] != 1){
                Pair tail = q.poll();
                board[tail.x][tail.y] = 0;
            }

            board[nextX][nextY] = 2;
            q.add(new Pair(nextX, nextY));
            time++;
            if (!rotate.isEmpty() && rotate.peek().second == time) {
                System.out.print("change: " + dir);
                Dir cur = rotate.poll();
                if (cur.dir.equals("L")) {
                    dir = (4 + dir - 1) % 4;
                } else {
                    dir = (dir + 1) % 4;
                }
                System.out.println("\t => " + dir);
            }

            nextX = nextX + dx[dir];
            nextY = nextY + dy[dir];
        }
        return time;
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            board[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 1;
        }
        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arrayList.add(new Dir(Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        int finish = game();
        System.out.println("finish = " + finish);
    }
}
/*
6
3
3 4
2 5
5 3
3
3 D
15 L
17 D
--- 9
10
4
1 2
1 3
1 4
1 5
4
8 D
10 D
11 D
13 L
--- 21
10
5
1 5
1 3
1 2
1 6
1 7
4
8 D
10 D
11 D
13 L
--- 13

 */
