package Daou.groom.test4;

public class Q5 {
    public static String solution(int n) {
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < n; i++) {
            answer.append(String.valueOf((i % 9) + 1));
            answer = answer.reverse();
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        int n = 5;
        String ret = solution(n);

        System.out.println("solution 함수의 반환 값은 " + ret + " 입니다.");
    }
}
