package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q5658 {
    private static int tc, N, K, rotate;
    private static Set<Integer> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 총 숫자 수
            int K = Integer.parseInt(st.nextToken()); // K번째로 큰 수
            int rotate = N / 4; // 한 면의 길이

            String line = br.readLine(); // 16진수 문자열
            Set<Integer> uniqueNumbers = new HashSet<>();

            // 총 rotate 번 회전
            for (int r = 0; r < rotate; r++) {
                for (int i = 0; i < 4; i++) {
                    int start = i * rotate;
                    int end = start + rotate;
                    String part = line.substring(start, end);
                    int number = Integer.parseInt(part, 16);
                    uniqueNumbers.add(number);
                }

                // 문자열 회전 (오른쪽으로 1칸)
                line = line.charAt(N - 1) + line.substring(0, N - 1);
            }

            // 내림차순 정렬
            List<Integer> sortedList = new ArrayList<>(uniqueNumbers);
            Collections.sort(sortedList, Collections.reverseOrder());

            int answer = sortedList.get(K - 1);
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }

    public static int getInteger(String line) {
        // line은 16진수
        int size = line.length();
        int num = 0;
        for (int i = 0; i < size; i++) {
            if (line.charAt(i) >= 'A' && line.charAt(i) <= 'F') {
                num = num * 16 + (line.charAt(i)-'A' + 10);
            } else {
                num = num * 16 + line.charAt(i) - '0';
            }
        }
        return num;
    }
}
/*
2
12 10
1B3B3B81F75E
16 2
F53586D76286B2D8
 */
