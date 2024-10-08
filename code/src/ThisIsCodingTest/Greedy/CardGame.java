package ThisIsCodingTest.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CardGame {
//    3 3
//    3 1 2
//    4 1 4
//    2 2 2
    // ans: 2

//    2 4
//    7 3 1 8
//    3 3 3 4
    // ans: 3

    // N 은 행의 갯수, M은 열의 갯수
    public static int N, M;
    public static int[][] board = new int[100][100];

    public static void main(String[] args) throws IOException {
//        prev();
        oct8();
    }

    private static void oct8() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.MAX_VALUE;
            for (int j = 0; j < M; j++) {
                row = Math.min(row, Integer.parseInt(st.nextToken()));
            }
            ans = Math.max(ans, row);
        }

        System.out.println(ans);
    }

    private static void prev() throws IOException {
        // 행 안에서 가장 작은 수
        // 전체 행에서 가장 큰 수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = -1;
        for(int i = 0; i < N; i++){
            // 행에서 가장 작은 값 추출
            int std = 10001;
            for(int j = 0; j < M; j++){
                if(std > board[i][j]){
                    std = board[i][j];
                }
            }
            // 행 간 최대 값 비교
            if(std > ans){
                ans = std;
            }
        }

        System.out.println("ans = " + ans);
    }
}
