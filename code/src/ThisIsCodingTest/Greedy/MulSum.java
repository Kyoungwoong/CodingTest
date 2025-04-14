package ThisIsCodingTest.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MulSum {
    public static void main(String[] args) throws IOException {
//        prev();
        oct28();
    }

    private static void oct28() throws IOException {
        // 각 자리가 0~9로 이루어진 문자열 S.
        // 왼쪽부터 오른쪽으로 하나씩 숫자 사이에 * or + 넣어 만들 수 있는 가장 큰 수.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        int len = S.length();

        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (S.charAt(i) != '0' || S.charAt(i) != '1') {
                if (ans != 0) {
                    ans *= S.charAt(i) - '0';
                } else {
                    ans += S.charAt(i) - '0';
                }
            } else {
                ans += S.charAt(i) - '0';
            }
        }
        System.out.println("ans = " + ans);
    }

    private static void prev() throws IOException {
        // 각 자리가 0~9로 이루어진 문자열 S.
        // 왼쪽부터 오른쪽으로 하나씩 숫자 사이에 * or + 넣어 만들 수 있는 가장 큰 수.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        int len = S.length();

        int ans = S.charAt(0) - '0';
        for(int i = 1; i < len; i++){
            ans = Math.max(ans + (S.charAt(i) - '0'), ans * (S.charAt(i) - '0'));
        }
        System.out.println("ans = " + ans);
    }
}
/*
02984 => 576

567 => 210
 */
