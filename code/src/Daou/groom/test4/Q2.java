package Daou.groom.test4;

public class Q2 {
    public static String solution(String s) {
        s = s.toLowerCase();
        String answer = "";

        char previous = s.charAt(0);
        int counter = 1;

        for (int i = 1; i < s.length(); i++) {
            char alphabet = s.charAt(i);
            if (alphabet == previous) {
                counter += 1;
            } else {
                answer += previous + String.valueOf(counter);
                counter = 1;
                previous = s.charAt(i);
            }
        }

        answer += previous + String.valueOf(counter);
        return answer;
    }

    public static void main(String[] args) {
        String s = "YYYYYbbbBbbBBBMmmM";
        String ret = solution(s);

        System.out.println("solution 함수의 반환 값은 " + ret + " 입니다.");
    }
}
