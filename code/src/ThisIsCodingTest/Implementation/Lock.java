package ThisIsCodingTest.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

class Dot implements Comparable<Dot>{
    int x, y;
    public Dot(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Dot d){
        return this.x - d.x;
    }
}

public class Lock {
    public static int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
    public static int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
    public static int[][] copy;
    public static ArrayList<Dot> home = new ArrayList<>();
    public static int N, M;

    public static boolean isRange(Dot d){
        return 0 <= d.x && d.x < N && 0 <= d.y && d.y < N;
    }

    public static boolean check(int[][] temp){
        for(int i = N; i < N*2; i++){
            for(int j = N; j < N*2; j++){
                if(temp[i][j] != 1){
                    return false;
                }
            }
        }
        return true;
    }

    public static int[][] rotate(int[][] a) {
        int n = a.length;
        int m = a[0].length;
        int[][] result = new int[n][m]; // 결과 리스트
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[j][n - i - 1] = a[i][j];
            }
        }
        return result;
    }
    
    public static void main(String[] args) throws IOException {
        // lock의 모든 0 부분에 key의 1이 들어가게.
        // 시계방향 회전, 상하좌우 이동 모두 가능.
        // 열 수 있으면 true, 없으면 false
        M = key.length; // M = 3
        N = lock.length; // N = 3
        int[][] newLock = new int[N * 3][N * 3];
        // 새로운 자물쇠의 중앙 부분에 기존의 자물쇠 넣기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newLock[i + N][j + N] = lock[i][j];
            }
        }

        for(int rot = 1; rot <= 4; rot++){
            key = rotate(key);
            for(int x = 0; x < N*2; x++){
                for(int y = 0; y < N*2; y++){
                    for (int i = 0; i < M; i++) {
                        for (int j = 0; j < M; j++) {
                            newLock[x + i][y + j] += key[i][j];
                        }
                    }
                    if(check(newLock)) System.out.println("true = " + true);
                    for (int i = 0; i < M; i++) {
                        for (int j = 0; j < M; j++) {
                            newLock[x + i][y + j] -= key[i][j];
                        }
                    }
                }
            }
        }
        System.out.println("false" );
    }
}
/*
key                                 lock                               result
[[0,0,0], [1,0,0], [0,1,1]]         [[1,1,1], [1,1,0], [1,0,1]]         true
 */
