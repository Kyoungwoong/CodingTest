package CodeTree.IntermediateLow.Simulation.first;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class PosSquare {
    private static int MAXSIZE = 20;
    private static int n, m;
    private static int[][] arr = new int[MAXSIZE][MAXSIZE];

    public static boolean positiveRect(int x1, int y1, int x2, int y2) {
        for(int i = x1; i <= x2; i++)
            for(int j = y1; j <= y2; j++)
                if(arr[i][j] <= 0)
                    return false;

        return true;
    }

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        } // 입력까지 잘 받아짐

        int ans = -1;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                // i, j start position
                for(int p = i; p < n; p++) {
                    for(int q = j; q < m; q++) {
                        // p, q end position
                        if(positiveRect(i, j, p, q))
                            ans = Math.max(ans, (p - i + 1) * (q - j + 1));
                    }
                }
            }
        }
        System.out.println(ans);
    }
}