package Programmers.stackqueue;

import java.util.ArrayList;
import java.util.List;

public class FuncDev {
//    private static int[] progresses = {93, 30, 55};
//    private static int[] speeds = {1, 30, 5};

    private static int[] progresses = {95, 90, 99, 99, 80, 99};
    private static int[] speeds = {1, 1, 1, 1, 1, 1};

    static class Dev {
        int progress, speed;

        public Dev(int progress, int speed) {
            this.progress = progress;
            this.speed = speed;
        }
    }

    private static List<Dev> kanban = new ArrayList<>();
    private static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) {
        int len = progresses.length;
        int day = 0;
        int count = 0;

        for (int i = 0; i < len; i++) {
            int requiredDays = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);

            if (day < requiredDays) {
                if (count > 0) {
                    answer.add(count);
                }
                day = requiredDays;
                count = 1;
            } else {
                count++;
            }
        }

        answer.add(count);

        System.out.println("answer = " + answer);
//        return answer.stream().mapToInt(Integer::intValue).toArray();

    }
}
