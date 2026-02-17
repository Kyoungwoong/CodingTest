package Daou.groom.test3;

public class Q3 {

    public static int solution(String[] bishops) {
        int answer = 64;

        int[] dx = {-1, -1, 1, 1};
        int[] dy = {-1, 1, 1, -1};
        boolean[][] visited = new boolean[8][8];

        for (String bishop : bishops) {
            int cnt = 0;
            for (int dir = 0; dir < 4; dir++) {
                int y = bishop.charAt(0) - 'A';
                int x = bishop.charAt(1) - '1';
                while (canGo(x, y)) {
                    if (!visited[x][y]) {
                        cnt++;
                    }
                    visited[x][y] = true;
                    x += dx[dir];
                    y += dy[dir];
                }
            }
            answer -= cnt;
        }

        return answer;
    }

    private static boolean canGo(int x, int y) {
        return 0 <= x && x < 8 && 0 <= y && y < 8;
    }

    public static void main(String[] args) {
        String[] bishops1 = {"D5"};
        int ret1 = solution(bishops1);

        System.out.println("solution 함수의 반환 값은 " + ret1 + " 입니다.");

        String[] bishops2 = {"D5", "E8", "G2"};
        int ret2 = solution(bishops2);

        System.out.println("solution 함수의 반환 값은 " + ret2 + " 입니다.");
    }
}
