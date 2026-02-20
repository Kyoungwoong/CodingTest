package Daou.groom.test4;

public class Q9 {
    public static String solution(int hour, int minute) {
        double answer = 0;

        // 1분당 각도는 360 % 60 = 6도

        // 1시간당 각도는 360 % 12 = 30도
        // 근데 1분 이동할 때마다 30 % 60 => 0.5도

        double hourAngle = (30) * hour + 0.5 * minute;
        double minuteAngle = 6 * minute;

        answer = Math.abs(hourAngle - minuteAngle);
        answer = Math.min(answer, 360 - answer);

        // 여기에 코드를 작성해주세요.

        return String.format("%.1f", answer);
    }

    public static void main(String[] args) {
        int hour = 3;
        int minute = 0;
        String ret = solution(hour, minute);

        System.out.println("solution 함수의 반환 값은 " + ret + " 입니다.");
    }
}
