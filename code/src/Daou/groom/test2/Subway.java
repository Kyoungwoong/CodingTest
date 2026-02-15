package Daou.groom.test2;

import java.util.*;

public class Subway {

    public static int funcA(String times) {
        int hour = Integer.parseInt(times.substring(0, 2));
        int minute = Integer.parseInt(times.substring(3));
        return hour * 60 + minute;
    }

    public static int solution(String[] subwayTimes, String currentTime) {

        int currentMinute = funcA(currentTime);   // 빈칸 ①
        final int INF = 1000000000;
        int answer = INF;

        for (String s : subwayTimes) {
            int subwayMinute = funcA(s);   // 빈칸 ②

            if (subwayMinute >= currentMinute) {         // 빈칸 ③
                answer = subwayMinute - currentMinute;
                break;
            }
        }

        if (answer == INF) {
            return -1;
        }

        return answer;
    }

    public static void main(String[] args) {

        String[] subwayTimes1 = {"05:31", "11:59", "13:30", "23:32"};
        String currentTime1 = "12:00";
        int ret1 = solution(subwayTimes1, currentTime1);
        System.out.println("solution 함수의 반환 값은 " + ret1 + " 입니다.");

        String[] subwayTimes2 = {"14:31", "15:31"};
        String currentTime2 = "15:31";
        int ret2 = solution(subwayTimes2, currentTime2);
        System.out.println("solution 함수의 반환 값은 " + ret2 + " 입니다.");
    }
}

