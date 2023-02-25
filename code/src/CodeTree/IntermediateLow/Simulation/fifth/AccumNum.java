package CodeTree.IntermediateLow.Simulation.fifth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

class Pair{
    int row, col;

    public Pair(int x, int y){
        row = x;
        col = y;
    }
}

public class AccumNum {
    private static int MAXSIZE = 20;
    private static int n, m;

    private static ArrayList<Integer>[][] arr = new ArrayList[MAXSIZE][MAXSIZE];

    public static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static Pair findTarget(int targetNum){
        Pair pair = new Pair(-1,-1);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < (int)arr[i][j].size(); k++){
                    if(targetNum == arr[i][j].get(k)){
                        pair = new Pair(i, j);
                    }
                }
            }
        }
        return pair;
    }

    public static void moveTarget(Pair targetIdx, int targetNum){
        int max = -1, maxRow = -1, maxCol = -1, idx = 0;
        //          북  북동  동  남동  남  남서  서  북서
        int[] dx = {-1, -1, 0,  1,  1,  1,  0, -1};
        int[] dy = {0,  1,  1,  1,  0,  -1, -1, -1};

        // 타겟 넘버의 인덱스 찾기
        for(int i = 0; i < (int)arr[targetIdx.row][targetIdx.col].size(); i++) {
            if(targetNum == arr[targetIdx.row][targetIdx.col].get(i)) {
                idx = i;
            }
        }

        // 8방향중 최대 값이 있는 방향 찾기
        for(int dir = 0; dir < 8; dir++) {
            int nx = targetIdx.row + dx[dir];
            int ny = targetIdx.col + dy[dir];

            if(!isRange(nx, ny)){
                continue;
            }

            for(int k = 0; k <(int)arr[nx][ny].size(); k++) {
                if(max < arr[nx][ny].get(k)){
                    max = arr[nx][ny].get(k);
                    maxRow = nx;
                    maxCol = ny;
                }
            }
        }
        if(isRange(maxRow, maxCol)){
            for(int i = idx; i >= 0 ; i--){
                arr[maxRow][maxCol].add(0, arr[targetIdx.row][targetIdx.col].get(i));
            }
            for(int i = 0; i < idx+1; i++) {
                arr[targetIdx.row][targetIdx.col].remove(0);
            }

            // for(int i = 0; i < n; i++) {
            //     for(int j = 0; j < n; j++) {
            //         System.out.print("i is " + i + " j is " + j + " value is ");
            //         for(int k = 0; k < (int)arr[i][j].size(); k++) {
            //             System.out.print(arr[i][j].get(k) + " ");
            //         }
            //         System.out.println();
            //     }

            // }
        }


    }

    public static void simulate(int targetNum){
        // 타겟 넘버 위치 찾기
        Pair targetIdx = findTarget(targetNum);
        // 타겟 넘버 옮기기
        moveTarget(targetIdx, targetNum);
    }

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                arr[i][j] = new ArrayList<>();
            }
        }

        // 배열에 값 넣어주기
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                arr[i][j].add(Integer.parseInt(st.nextToken()));
            }
        }

        // 1 7 4 1 실행
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            simulate(Integer.parseInt(st.nextToken()));
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) {
                if(arr[i][j].size() == 0){
                    System.out.println("None");
                }
                else{
                    for(int k = 0; k <(int)arr[i][j].size(); k++) {
                        System.out.print(arr[i][j].get(k) + " ");
                    }
                    System.out.println();
                }
            }
        }

    }
}