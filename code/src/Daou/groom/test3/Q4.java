package Daou.groom.test3;

public class Q4 {

    public static int solution(String s1, String s2) {
        int answer = 0;

        int s1Len = s1.length();
        int s2Len = s2.length();

        // s1 + s2
        StringBuilder s1s2 = new StringBuilder();
        s1s2.append(s1).append(s2);
        // 같은 지 확인
        for (int i = s1Len - 1; i >= 0; i--) {
            if (s1.charAt(i) == s2.charAt(0)) {
                int size = s1Len - i;
                if (s2.length() >= size && s1.substring(i).equals(s2.substring(0, size))) {
                    s1s2 = new StringBuilder();
                    s1s2.append(s1).append(s2.substring(size));
                }
            }
        }

        // s2 + s1
        StringBuilder s2s1 = new StringBuilder();
        s2s1.append(s2).append(s1);
        // 같은 지 확인
        for (int i = s2Len - 1; i >= 0; i--) {
            if (s2.charAt(i) == s1.charAt(0)) {
                int size = s2Len - i;
                if (s1.length() >= size && s2.substring(i).equals(s1.substring(0, size))) {
                    s2s1 = new StringBuilder();
                    s2s1.append(s2).append(s1.substring(size));
                }
            }
        }

        answer = Math.min(s1s2.length(), s2s1.length());

        return answer;
    }

    public static void main(String[] args) {
        String s1 = "ababc";
        String s2 = "abcdab";
        int ret = solution(s1, s2);

        System.out.println("solution 함수의 반환 값은 " + ret + " 입니다.");

        s1 = "ababc";
        s2 = "ababc";
        ret = solution(s1, s2);

        System.out.println("solution2 함수의 반환 값은 " + ret + " 입니다.");
    }
}
