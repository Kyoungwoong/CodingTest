package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q4613 {

    private static char[] colors = new char[]{'W', 'B', 'R'};
    private static int tc, N, M, ans;
    private static char[][] flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        tc = Integer.parseInt(br.readLine());

        for (int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            flag = new char[N][M];
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    flag[i][j] = line.charAt(j);
                }
            }

            ans = Integer.MAX_VALUE;
            solve(0, 0, 0);

            sb.append(String.format("#%d %d\n", t, ans));
        }
        System.out.println(sb);
    }

    private static void solve(int row, int accumlateCnt, int color) {
        if (row == N && (color == 3 || color == 2)) {
            ans = Math.min(ans, accumlateCnt);
            return;
        }

        if (color == 3 || row >= N ||
                (row == N - 2 && colors[color] == 'W') ||
                (row == N - 1 && colors[color] == 'B')) {
            return;
        }

        int changeCnt = 0;
        for (int i = 0; i < M; i++) {
            if (flag[row][i] != colors[color]) {
                changeCnt++;
            }
        }

//        System.out.println("row: " + row +
//                " cnt: " + (accumlateCnt + changeCnt) +
//                " color: " + colors[color]);

        solve(row + 1, accumlateCnt + changeCnt, color + 1);
        solve(row + 1, accumlateCnt + changeCnt, color);
    }
}

/*
2
4 5
WRWRW
BWRWB
WRWRW
RWBWR
6 14
WWWWWWWWWWWWWW
WWRRWWBBBBBBWW
WRRRWWWBWWWWRB
WWBWBWWWBWRRRR
WBWBBWWWBBWRRW
WWWWWWWWWWWWWW
 */