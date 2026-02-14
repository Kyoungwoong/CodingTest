package Daou.Programmers;

import java.util.*;

public class Q10 {
    public int solution(int K, String[] words) {
        // 여기에 코드를 작성해주세요.
        int answer = 0;
        int n = words.length;

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(words[i]);
            while (sb.length() < K) {
                if (sb.length() + 1 + words[i + 1].length() <= K) {
                    sb.append("_").append(words[i++]);
                } else {
                    break;
                }
            }
            answer++;
        }

        return answer;
    }

    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.
    public static void main(String[] args) {
        Q10 sol = new Q10();
        int K = 10;
        String[] words = {new String("nice"), new String("happy"), new String("hello"), new String("world"), new String("hi")};
        int ret = sol.solution(K, words);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소의 반환 값은 " + ret + " 입니다.");
    }
}