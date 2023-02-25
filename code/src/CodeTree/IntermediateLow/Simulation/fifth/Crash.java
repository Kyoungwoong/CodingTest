package CodeTree.IntermediateLow.Simulation.fifth;

import java.util.Scanner;
import java.util.ArrayList;

class Marble{
    int x, y, z;
    public Marble(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

public class Crash{
    private static final int ASCII_NUM = 128;
    private static final int MAX_N = 50;
    private static final int DIR_NUM = 4;

    private static int n, m, t;
    private static int[][] marbleCnt = new int[MAX_N + 1][MAX_N + 1];
    private static int[] mapper = new int[ASCII_NUM];

    // 후에 구슬이 벽에 부딪혔을 때의 처리를 간단히 하기 위해
    // dir 기준 0, 3이 대칭 1, 2가 대칭이 되도록 설정합니다.
    public static int[] dx = new int[]{-1, 0, 0, 1};
    public static int[] dy = new int[]{0, 1, -1, 0};

    public static ArrayList<Marble> marbles = new ArrayList<>();

    // 격자 내에 있는 지 확인
    public static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0<= y && y < n;
    }

    // after 1s
    public static Marble move(Marble marble){
        int x = marble.x;
        int y = marble.y;
        int dir = marble.z;

        int nx = x + dx[dir], ny = y + dy[dir];

        if(isRange(nx, ny)){
            return new Marble(nx, ny, dir);
        }
        else{
            return new Marble(x, y, 3-dir);
        }
    }

    public static void moveAll(){
        for(int i =0; i <(int)marbles.size(); i++) {
            Marble nextMarble = move(marbles.get(i));
            marbles.set(i, nextMarble);
        }
    }

    public static boolean duplicateMarbleExist(int targetIdx) {
        int targetX = marbles.get(targetIdx).x;
        int targetY = marbles.get(targetIdx).y;

        return marbleCnt[targetX][targetY] >= 2;
    }

    public static void rmMarble(){
        ArrayList<Marble> tempVector = new ArrayList<>();

        for(int i = 0; i <(int)marbles.size(); i++) {
            int x = marbles.get(i).x;
            int y = marbles.get(i).y;
            marbleCnt[x][y]++;
        }

        // Step2-2 : 충돌이 일어나지 않는 구슬만 전부 기록합니다.
        for(int i = 0; i < (int) marbles.size(); i++)
            if(!duplicateMarbleExist(i))
                tempVector.add(marbles.get(i));

        // Step2-3 : 나중을 위해 각 구슬의 위치에 적어놓은 count 수를 다시 초기화합니다.
        for(int i = 0; i < (int) marbles.size(); i++) {
            int x = marbles.get(i).x;
            int y = marbles.get(i).y;
            marbleCnt[x][y]--;
        }

        // step2-4 : 충돌이 일어나지 않은 구슬들로 다시 채워줍니다.
        marbles = tempVector;

    }

    public static void simulate(){
        // 구슬 움직이기
        moveAll();

        // 움직인 후 충돌 제거해주기
        rmMarble();
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        mapper['U'] = 0;
        mapper['R'] = 1;
        mapper['L'] = 2;
        mapper['D'] = 3;

        // 테스트 케이스 수 입력:
        t = sc.nextInt();
        while(t-- > 0){
            // 그때 그때마다 새로 초기화
            marbles = new ArrayList<>();

            // 입력:
            n = sc.nextInt();
            m = sc.nextInt();
            for(int i = 0; i < m; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                char d = sc.next().charAt(0);
                marbles.add(new Marble(x-1, y-1, mapper[d]));
            }

            for(int i = 0; i < 2*n; i++) {
                simulate();
            }

            System.out.println((int)marbles.size());
        }
    }
}