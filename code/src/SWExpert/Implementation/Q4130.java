package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
하나의 자석이 1 칸 회전될 때, 붙어 있는 자석은 서로 붙어 있는 날의 자성과 다를 경우에만 인력에 의해 반대 방향으로 1 칸 회전된다.

이를 신기하게 생각한 선표는 무작위로 자석을 돌렸을 때, 모든 회전이 끝난 후, 아래와 같은 방법으로 점수를 계산을 하고자 한다.

- 1 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 1 점을 획득한다.

- 2 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 2 점을 획득한다.

- 3 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 4 점을 획득한다.

- 4 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 8 점을 획득한다.
 */
public class Q4130 {
    static class Gear {
        int[] poles = new int[8];
    }
    private static int tc, K;
    private static Gear[] gears = new Gear[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= tc; t++) {
            K = Integer.parseInt(br.readLine());
            gears = new Gear[4];
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                gears[i] = new Gear();
                for (int j = 0; j < 8; j++) {
                    gears[i].poles[j] = Integer.parseInt(st.nextToken());
                }
            }

//            for (int g = 0; g < 4; g++) {
//                for (int h = 0; h < 8; h++) {
//                    System.out.print(gears[g].poles[h] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println("=======================");

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken()) - 1;
                int dir = Integer.parseInt(st.nextToken());
                boolean[] visited = new boolean[4];
                visited[idx] = true;
                rotate(idx, dir, visited);

//                for (int g = 0; g < 4; g++) {
//                    for (int h = 0; h < 8; h++) {
//                        System.out.print(gears[g].poles[h] + " ");
//                    }
//                    System.out.println();
//                }
            }

            int sum = 0;
            for (int i = 0; i < 4; i++) {
                sum += (gears[i].poles[0] * (1 << i));
            }
            sb.append("#").append(t).append(" ").append(sum).append("\n");
        }
        System.out.println(sb);
    }

    private static void rotate(int idx, int dir, boolean[] visited) {
        Gear gear = gears[idx];
        visited[idx] = true;

        if (idx > 0 && !visited[idx - 1]) {
            Gear leftGear = gears[idx - 1];
            if (leftGear.poles[2] != gear.poles[6]) {
                rotate(idx - 1, -dir, visited);
            }
        }

        if (idx < 3 && !visited[idx + 1]) {
            Gear rightGear = gears[idx + 1];
            if (rightGear.poles[6] != gear.poles[2]) {
                rotate(idx + 1, -dir, visited);
            }
        }

        simulate(idx, dir); // 현재 톱니 회전
    }

    private static void simulate(int idx, int dir) {
        Gear gear = gears[idx];
        if (dir == 1) { // 시계방향
            int temp = gear.poles[7];
            for (int i = 7; i > 0; i--) {
                gear.poles[i] = gear.poles[i - 1];
            }
            gear.poles[0] = temp;
        } else { // 반시계 방향
            int temp = gear.poles[0];
            for (int i = 0; i < 7; i++) {
                gear.poles[i] = gear.poles[i + 1];
            }
            gear.poles[7] = temp;
        }
    }
}
/*
10
2
0 0 1 0 0 1 0 0
1 0 0 1 1 1 0 1
0 0 1 0 1 1 0 0
0 0 1 0 1 1 0 1
1 1
3 -1
2
1 0 0 1 0 0 0 0
0 1 1 1 1 1 1 1
0 1 0 1 0 0 1 0
0 1 0 0 1 1 0 1
3 1
1 1
5
0 0 1 1 1 1 1 1
1 1 1 1 1 0 1 0
0 0 0 0 1 0 0 1
0 1 0 1 0 1 0 1
4 -1
3 1
4 -1
3 -1
1 -1
2
1 0 1 0 0 1 0 1
0 0 1 0 1 1 1 1
0 0 1 1 0 0 0 1
0 1 0 1 1 0 0 0
2 -1
1 1
7
0 0 1 1 0 1 1 1
0 1 0 1 1 0 0 0
1 1 1 0 0 0 0 1
1 1 1 0 0 1 0 0
4 1
2 1
2 -1
3 1
2 1
4 1
2 -1
10
1 0 0 0 0 0 0 1
1 0 1 0 1 1 0 1
1 0 0 1 0 0 0 1
1 1 0 1 0 1 1 1
2 1
1 1
2 -1
3 1
3 -1
2 -1
2 -1
1 1
4 1
4 1
10
0 1 0 0 1 1 0 0
0 1 1 0 1 0 1 1
0 0 0 0 0 1 1 0
0 0 1 0 1 0 1 1
3 1
1 -1
2 1
4 -1
3 1
3 -1
4 -1
2 -1
1 -1
3 -1
10
0 1 0 1 0 1 0 0
0 1 1 1 1 1 0 1
1 0 0 0 0 1 1 0
1 0 0 0 0 0 0 1
1 1
4 -1
4 -1
2 -1
2 -1
2 -1
3 -1
2 1
3 1
3 -1
20
1 0 0 0 1 1 0 0
1 0 0 1 1 1 0 0
0 1 1 1 0 1 1 1
1 1 1 1 0 1 1 1
1 1
4 -1
4 -1
2 -1
3 -1
1 1
4 1
4 -1
4 -1
4 -1
3 -1
3 -1
4 -1
4 -1
2 -1
1 1
3 -1
3 -1
2 1
1 1
20
0 0 1 1 1 0 1 0
0 1 0 0 1 0 1 0
1 1 1 0 1 0 1 0
0 0 1 0 0 1 1 1
1 -1
4 -1
3 -1
1 1
4 1
2 1
1 -1
4 1
2 -1
4 -1
1 1
4 -1
1 1
2 -1
1 -1
3 -1
1 1
2 1
3 1
3 -1
 */
