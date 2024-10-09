package ThisIsCodingTest.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Game {
//    4 4
//            1 1 0
//            1 1 1 1
//            1 0 0 1
//            1 1 0 1
//            1 1 1 1
//    ans = 3
    public static int N, M, x, y, cmd;
    public static int[][] map = new int[50][50];

    public static boolean isRange(int x, int y){
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static boolean canGo(int x, int y){
        if(!isRange(x, y)){
            return false;
        }
        if(map[x][y] == 1 || map[x][y] == 2) return false;
        return true;
    }

    public static void main(String[] args) throws IOException {
//        prev();
        oct9();
    }

    private static void oct9() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int ans = 1;
        map[0][0] = 2;
        boolean flag = false;
        while(true) {
            flag = false;
            for (int i = 0; i < 3; i++) {
                dir = (dir - 1 + 4) % 4; // 0 => 3, 1 => 0, 2 => 1, 3 => 2
                int nextX = x + dx[dir];
                int nextY = y + dy[dir];
                System.out.print("dir: " + dir + " (nextX, nextY) = " + "("+ nextX+", " + nextY+")");
                if (canGo(nextX, nextY)) {
                    System.out.println("\t YES");
                    x = nextX;
                    y = nextY;
                    map[x][y] = 2;
                    ans++;
                    flag = true;
                    break;
                }
                System.out.println();
            }
            if (!flag) {
                // 0 => 2, 1 => 3, 2 => 0, 3 => 1
                int opp = (dir + 2) % 4;
                int nextX = x + dx[opp];
                int nextY = y + dy[opp];
                if (isRange(nextX, nextY) && map[nextX][nextY] == 1) {
                    break;
                }
            }
        }
        System.out.println("ans = " + ans);
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        cmd = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 1;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        while(true){
            boolean check = false;
            map[x][y] = 2;
            for(int idx = 0; idx < 4; idx++){
                int dir = (idx + cmd) % 4;
                int nextX = x + dx[dir];
                int nextY = y + dy[dir];
                System.out.println("nextX: " + nextX + " nextY: " + nextY);
                if(canGo(nextX, nextY)){
                    System.out.println("nextX: " + nextX + " nextY: " + nextY);
                    check = true;
                    x = nextX;
                    y = nextY;
                    cmd = dir;
                    ans++;
                    break;
                }
            }
            if(!check){
                int dir = (cmd + 2) % 4;
                int nextX = x + dx[dir];
                int nextY = y + dy[dir];
                if(map[nextX][nextY] == 1){
                    break;
                }else{
                    x = nextX;
                    y = nextY;
                }
            }
        }
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
 */

/*
         version 나동빈
public class Main {

    public static int n, m, x, y, direction;
    // 방문한 위치를 저장하기 위한 맵을 생성하여 0으로 초기화
    public static int[][] d = new int[50][50];
    // 전체 맵 정보
    public static int[][] arr = new int [50][50];

    // 북, 동, 남, 서 방향 정의
    public static int dx[] = {-1, 0, 1, 0};
    public static int dy[] = {0, 1, 0, -1};

    // 왼쪽으로 회전
    public static void turn_left() {
        direction -= 1;
        if (direction == -1) direction = 3;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // N, M을 공백을 기준으로 구분하여 입력받기
        n = sc.nextInt();
        m = sc.nextInt();

        // 현재 캐릭터의 X 좌표, Y 좌표, 방향을 입력받기
        x = sc.nextInt();
        y = sc.nextInt();
        direction = sc.nextInt();
        d[x][y] = 1; // 현재 좌표 방문 처리

        // 전체 맵 정보를 입력 받기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        // 시뮬레이션 시작
        int cnt = 1;
        int turn_time = 0;
        while (true) {
            // 왼쪽으로 회전
            turn_left();
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            // 회전한 이후 정면에 가보지 않은 칸이 존재하는 경우 이동
            if (d[nx][ny] == 0 && arr[nx][ny] == 0) {
                d[nx][ny] = 1;
                x = nx;
                y = ny;
                cnt += 1;
                turn_time = 0;
                continue;
            }
            // 회전한 이후 정면에 가보지 않은 칸이 없거나 바다인 경우
            else turn_time += 1;
            // 네 방향 모두 갈 수 없는 경우
            if (turn_time == 4) {
                nx = x - dx[direction];
                ny = y - dy[direction];
                // 뒤로 갈 수 있다면 이동하기
                if (arr[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                }
                // 뒤가 바다로 막혀있는 경우
                else break;
                turn_time = 0;
            }
        }

        System.out.println(cnt);
    }


}
 */
