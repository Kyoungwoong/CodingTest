package Daou.Programmers;

import java.util.*;

public class Q8 {
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    Set<Integer> flowers = new HashSet<>();
    List<Pair> nextFlowers;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};

    public int solution(int[][] garden) {
        flowers = new HashSet<>();
        nextFlowers = new ArrayList<>();
        Queue<Pair> q = new ArrayDeque<>();

        // 여기에 코드를 작성해주세요.
        int days = 0;
        int n = garden.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (garden[i][j] == 1) {
                    Pair flower = new Pair(i, j);
                    flowers.add(i * n + j);
                    nextFlowers.add(flower);
                    q.add(flower);
                }
            }
        }

        while (flowers.size() != n * n) {
            Queue<Pair> nq = new ArrayDeque<>();
            while (!q.isEmpty()) {
                Pair cur = q.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nextX = cur.x + dx[dir];
                    int nextY = cur.y + dy[dir];

                    if (inRange(nextX, nextY, n) && !flowers.contains(nextX * n + nextY)) {
                        nq.add(new Pair(nextX, nextY));
                        flowers.add(nextX * n + nextY);
                    }
                }
            }
            q = nq;
            days++;
        }
        return days;
    }

    public boolean inRange(int x, int y, int n) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.
    public static void main(String[] args) {
        Q8 sol = new Q8();
        int[][] garden1 = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int ret1 = sol.solution(garden1);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret1 + " 입니다.");

        int[][] garden2 = {{1, 1}, {1, 1}};
        int ret2 = sol.solution(garden2);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret2 + " 입니다.");

    }
}