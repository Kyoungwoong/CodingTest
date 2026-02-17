package Daou.groom.test3;

public class Q5 {
    public static String solution(String phrases, int second) {
        StringBuilder answer = new StringBuilder();
        int len = phrases.length();
        int routine = len + 14;
        second %= routine;

        System.out.println(second);
        if (second >= 14) {
            answer.append(phrases.substring(second - 14, Math.min(len, second))); // 17
        } else {
            while (answer.length() + second != 14) {
                answer.append("_");
            }
            answer.append(phrases.substring(0, Math.min(second, len)));
        }
        while (answer.length() < 14) {
            answer.append("_");
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        String phrases = "happy-birthday";
        int second = 15;
        String ret = solution(phrases, second);

        System.out.println("solution 함수의 반환 값은 " + ret + " 입니다.");
    }
}
