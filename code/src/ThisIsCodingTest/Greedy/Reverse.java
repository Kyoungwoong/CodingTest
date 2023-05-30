package ThisIsCodingTest.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reverse {
    public static void main(String[] args) throws IOException {
        // 0과 1로만 이루어진 문자열 S
        // S에 있는 문자 전부 같게.
        // S에서 연속된 하나 이상의 숫자를 잡고 모두 뒤집는 것.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        int len = S.length();

        int cnt_0 = 0;
        int cnt_1 = 0;
        int ans = 0;
        int count = 0;

        // 0으로 만들기
        for(int i = 0; i < len; i++){
            if(S.charAt(i)-'0' == 0 && count != 0){
                cnt_0++;
                count = 0;
            }
            else if(S.charAt(i)-'0' == 1){
                count++;
            }

            if(i == len-1 && S.charAt(i)-'0' == 1){
                cnt_0++;
                count = 0;
            }
        }

        // 1로 만들기
        for(int i = 0; i < len; i++){
            if(S.charAt(i)-'0' == 1 && count != 0){
                cnt_1++;
                count = 0;
            }
            else if(S.charAt(i)-'0' == 0){
                count++;
            }

            if(i == len-1 && S.charAt(i)-'0' == 0){
                cnt_1++;
            }
        }

        // 두개 중 최소.
        ans = Math.min(cnt_0, cnt_1);
        System.out.println("ans = " + ans);
    }
}
/*
0001100
 */
