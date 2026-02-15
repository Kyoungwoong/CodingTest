package Daou.groom.test2;

import java.util.*;

public class MoveRobot {

    public static List<Integer> solution(String commands) {
        List<Integer> answer = new ArrayList<>();

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int x = 0, y = 0;

        int len = commands.length();
        for (int i = 0; i < len; i++) {
            int dir = -1;
            switch (commands.charAt(i)) {
                case 'U':
                    dir = 2;
                    break;
                case 'R':
                    dir = 1;
                    break;
                case 'D':
                    dir = 0;
                    break;
                case 'L':
                    dir = 3;
                    break;
            }
            x += dx[dir];
            y += dy[dir];
        }

        answer.add(x);
        answer.add(y);
        return answer;
    }

    public static void main(String[] args) {

        String commands = "URDDL";
        List<Integer> ret = solution(commands);

        System.out.println("solution 함수의 반환 값은 " + ret + " 입니다.");
    }
}
