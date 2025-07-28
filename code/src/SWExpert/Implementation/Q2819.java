package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Q2819 {
    private static int tc;
    private static int[][] nums;
    private static Set<String> s;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        nums = new int[4][4];

        for (int t = 1; t <= tc; t++) {
            sb.append("#").append(t).append(" ");
            s = new HashSet<>();

            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    nums[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    findNums(0, i, j, "" + nums[i][j]);
                }
            }

//            for (String num : s) {
//                System.out.println(num);
//            }

            sb.append(s.size()).append("\n");
        }
        System.out.println(sb);
    }

    public static void findNums(int depth, int x, int y, String num) {
        if (depth == 6) {
            s.add(num);
            return;
        }

        for (int idx = 0; idx < 4; idx++) {
            int nx = x + dx[idx];
            int ny = y + dy[idx];
            if (inRange(nx, ny)) {
                findNums(depth + 1, nx, ny, num + nums[nx][ny]);
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < 4 && 0 <= y && y < 4;
    }
}
/*
1
1 1 1 1
1 1 1 2
1 1 2 1
1 1 1 1
 */
