package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Q1406 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();           // 초기 문자열
        int M = Integer.parseInt(br.readLine()); // 명령 개수

        // 커서 왼쪽 문자들, 오른쪽 문자들
        Deque<Character> left = new ArrayDeque<>();
        Deque<Character> right = new ArrayDeque<>();

        // 초기 문자열을 전부 왼쪽 스택에, 커서는 최초에 가장 오른쪽에 있으니까
        for (int i = 0; i < line.length(); i++) {
            left.push(line.charAt(i));
        }

        StringTokenizer st;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch (command) {
                case "L":
                    // 커서를 왼쪽으로: 왼쪽 스택에서 오른쪽 스택으로 한 글자 이동
                    if (!left.isEmpty()) {
                        right.push(left.pop());
                    }
                    break;
                case "D":
                    // 커서를 오른쪽으로: 오른쪽 스택에서 왼쪽 스택으로 한 글자 이동
                    if (!right.isEmpty()) {
                        left.push(right.pop());
                    }
                    break;
                case "B":
                    // 커서 왼쪽 문자 삭제
                    if (!left.isEmpty()) {
                        left.pop();
                    }
                    break;
                case "P":
                    // 문자 추가: 커서 왼쪽(=left 스택)에 push
                    char ch = st.nextToken().charAt(0);
                    left.push(ch);
                    break;
            }
        }

        // 최종 문자열 만들기
        // left에 들어있는 것들을 모두 right로 옮긴 후
        while (!left.isEmpty()) {
            right.push(left.pop());
        }

        StringBuilder sb = new StringBuilder(right.size());
        while (!right.isEmpty()) {
            sb.append(right.pop());
        }

        System.out.println(sb.toString());
    }
}
