package ThisIsCodingTest.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lucky {
    public static String score;
    public static int len;

    public static int luckyStrike(int cmd){
        int mid = len/2 ;
        int sum = 0;
        switch (cmd){
            case 0:
                for(int i = 0; i < mid; i++){
                    sum += score.charAt(i)-'0';
                }
                break;
            case 1:
                for(int i = mid; i < len; i++){
                    sum += score.charAt(i)-'0';
                }
                break;
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
//        prev();
        oct30();
    }

    private static void oct30() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        score = br.readLine();
        len = score.length();

        int left_cnt = 0, right_cnt = 0;
        for (int i = 0; i < len; i++) {
            if (len / 2 > i) {
                left_cnt += score.charAt(i) - '0';
            } else {
                right_cnt += score.charAt(i) - '0';
            }
        }
        System.out.println(right_cnt == left_cnt ? "LUCKY" : "READY");
    }

    private static void prev() throws IOException {
        // 특정 조건을 만족할 때만 럭키 스트라이크 기술 가능
        // 현재 캐릭터의 점수 N 반으로 나누어 왼쪽 부분의 각 자리 숫자 합과 오른쪽 부분의 각 자리 숫자 합을 더한 값이 동일한 상황
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        score = br.readLine();
        len = score.length();

        if(luckyStrike(0) == luckyStrike(1)){
            System.out.println("LUCKY");
        }else{
            System.out.println("READY");
        }
    }
}
/*
123402

7755
 */
