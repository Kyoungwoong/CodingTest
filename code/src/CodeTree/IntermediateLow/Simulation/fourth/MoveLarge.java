package CodeTree.IntermediateLow.Simulation.fourth;

import java.util.Scanner;
import java.util.ArrayList;

public class MoveLarge {
    public static final int DIR_NUM = 4;
    public static final int MAX_N = 100;

    public static int n;
    public static int currX, currY; // 현재 위치를 의미합니다.
    public static int[][] a = new int[MAX_N + 1][MAX_N + 1];

    // 방문하게 되는 숫자들을 담을 곳입니다.
    public static ArrayList<Integer> visitedNums = new ArrayList<>();

    // 범위가 격자 안에 들어가는지 확인합니다.
    public static boolean inRange(int x, int y) {
        return 1 <= x && x <= n && 1 <= y && y <= n;
    }

    // 범위가 격자 안이고, 해당 위치의 값이 더 큰지 확인합니다.
    public static boolean canGo(int x, int y, int currNum) {
        return inRange(x, y) && a[x][y] > currNum;
    }

    // 조건에 맞춰 움직여봅니다.
    // 움직였다면 true를 반환하고
    // 만약 움직일 수 있는 곳이 없었다면 false를 반환합니다.
    public static boolean simulate() {
        int[] dx = new int[]{-1, 1,  0, 0};
        int[] dy = new int[]{ 0, 0, -1, 1};

        // 각각의 방향에 대해 나아갈 수 있는 곳이 있는지 확인합니다.
        for(int i = 0; i < 4; i++) {
            int nextX = currX + dx[i];
            int nextY = currY + dy[i];

            // 갈 수 있는 곳이라면
            // 이동하고 true를 반환합니다.
            if(canGo(nextX, nextY, a[currX][currY])) {
                currX = nextX;
                currY = nextY;
                return true;
            }
        }

        // 움직일 수 있는 곳이 없었다는 의미로
        // false 값을 반환합니다.
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();
        currX = sc.nextInt();
        currY = sc.nextInt();

        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= n; j++)
                a[i][j] = sc.nextInt();

        // 초기 위치에 적혀있는 값을 답에 넣어줍니다.
        visitedNums.add(a[currX][currY]);

        while(true) {
            // 조건에 맞춰 움직여봅니다.
            boolean greaterNumberExist = simulate();

            // 인접한 곳에 더 큰 숫자가 없다면 종료합니다.
            if(!greaterNumberExist)
                break;

            // 움직이고 난 후의 위치를 답에 넣어줍니다.
            visitedNums.add(a[currX][currY]);
        }

        // 출력:
        for(int i = 0; i < visitedNums.size(); i++)
            System.out.print(visitedNums.get(i) + " ");
    }
}