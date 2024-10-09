package ThisIsCodingTest.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class UDLR {

//    5
//    R R R U D D // ans: 3, 4
    public static int N;

    public static enum DIR {
        U(2),
        D(3),
        L(0),
        R(1);
        private int dir;

        DIR(int dir) {
            this.dir = dir;
        }

        public int getDir() {
            return dir;
        }
    }

    public static boolean isRange(int x, int y){
        return 1 <= x && x <= N && 1 <= y && y <= N;
    }

    public static boolean canGo(int x, int y){
//        if(!isRange(x, y)){
//            return false;
//        }
//        return true;
        return 1 <= x && x <= N && 1 <= y && y <= N;
    }

    public static void main(String[] args) throws IOException {
//        prev();
        oct9();
    }

    private static void oct9() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int x = 1, y = 1;
        st = new StringTokenizer(br.readLine());
        int[] dirX = {0, 0, -1, 1};
        int[] dirY = {-1, 1, 0, 0};

        while (st.hasMoreTokens()) {
            String move = st.nextToken();
            DIR dir = DIR.valueOf(move);
            int nextX = x + dirX[dir.getDir()];
            int nextY = y + dirY[dir.getDir()];

            if (isRange(nextX, nextY)) {
                x = nextX;
                y = nextY;
            }
        }

        System.out.println("(x, y) = (" + x + ", " + y + ")");
    };

    private static void prev() {
        // (1,1)의 위치에서 어디로 가는지
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        N = Integer.parseInt(br.readLine());
//        StringTokenizer st = new StringTokenizer(br.readLine());

        Scanner sc = new Scanner(System.in);

        // N을 입력받기
        N = sc.nextInt();
        sc.nextLine(); // 버퍼 비우기
        String[] plans = sc.nextLine().split(" ");

        int len = plans.length;
        int x = 1;
        int y = 1;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for(int i = 0; i < len; i++){
            int nextX;
            int nextY;
            switch (plans[i]){

                case "U":
                    nextX = x + dx[DIR.U.ordinal()];
                    nextY = y + dy[DIR.U.ordinal()];
                    if(canGo(nextX, nextY)){
                        x = nextX;
                        y = nextY;
                    }
                    break;
                case "D":
                    nextX = x + dx[DIR.D.ordinal()];
                    nextY = y + dy[DIR.D.ordinal()];
                    if(canGo(nextX, nextY)){
                        x = nextX;
                        y = nextY;
                    }
                    break;
                case "L":
                    nextX = x + dx[DIR.L.ordinal()];
                    nextY = y + dy[DIR.L.ordinal()];
                    if(canGo(nextX, nextY)){
                        x = nextX;
                        y = nextY;
                    }
                    break;
                case "R":
                    nextX = x + dx[DIR.R.ordinal()];
                    nextY = y + dy[DIR.R.ordinal()];
                    if(canGo(nextX, nextY)){
                        x = nextX;
                        y = nextY;
                    }
                    break;
            }
        }

        System.out.println(x + " " + y);

        /*
           나동빈 풀이
           // L, R, U, D에 따른 이동 방향
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        char[] moveTypes = {'L', 'R', 'U', 'D'};

        // 이동 계획을 하나씩 확인
        for (int i = 0; i < plans.length; i++) {
            char plan = plans[i].charAt(0);
            // 이동 후 좌표 구하기
            int nx = -1, ny = -1;
            for (int j = 0; j < 4; j++) {
                if (plan == moveTypes[j]) {
                    nx = x + dx[j];
                    ny = y + dy[j];
                }
            }
            // 공간을 벗어나는 경우 무시
            if (nx < 1 || ny < 1 || nx > n || ny > n) continue;
            // 이동 수행
            x = nx;
            y = ny;
        }

        System.out.println(x + " " + y);
         */
    }
}
