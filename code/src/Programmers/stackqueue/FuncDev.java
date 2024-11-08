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
//        int[] ans = prev();
        int[] ans = nov8();
    }

    private static int[] nov8() {
        List<Integer> production = new ArrayList<>();

        int len = progresses.length;
        int curDay = 0;
        int features = 0;

        for (int i = 0; i < len; i++) {
            // 남은 작업일 계산
            int remainDay = (100 - progresses[i]) / speeds[i];
            if ((100 - progresses[i]) % speeds[i] != 0) {
                remainDay++;
            }

            // 현재 기능이 배포 가능일보다 오래 걸리면 새로운 배포
            if (curDay < remainDay) {
                if (features > 0) {
                    production.add(features); // 이전에 쌓인 기능 수를 추가
                }
                curDay = remainDay; // 현재 배포 일자 갱신
                features = 1; // 새 기능 초기화
            } else {
                // 현재 기능이 이전 배포 일자 내에 완성되면 함께 배포
                features++;
            }
        }

        // 마지막에 남은 기능 수 추가
        if (features > 0) {
            production.add(features);
        }

        // 결과 배열로 변환
        return production.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int[] prev() {
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

//        System.out.println("answer = " + answer);
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
