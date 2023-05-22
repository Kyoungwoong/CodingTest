package ThisIsCodingTest.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Knight{
    int x, y;
    public Knight(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class P115 {
    public static boolean canGo(int x, int y){
        return 0 <= x && x < 8 && 0 <= y && y < 8;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cur = br.readLine();

        int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};

        int x = cur.charAt(1)-'1';
        int y = cur.charAt(0)-'a';
        int ans = 0;

        for(int idx = 0; idx < 8; idx++){
            int nextX = x + dx[idx];
            int nextY = y + dy[idx];
            if(canGo(nextX, nextY)){
                ans++;
            }
        }
        System.out.println("ans = " + ans);
    }
}
