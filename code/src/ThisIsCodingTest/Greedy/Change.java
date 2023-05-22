package ThisIsCodingTest.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Page 87
public class Change {
    public static int coin_500 = 500, coin_100 = 100, coin_50 = 50, coin_10 = 10, N;
    // coin들의 단위가 서로서로의 배수이기 때문에 그리디 적용 가능.

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 거스름 해줘야 하는 총액
        N = Integer.parseInt(br.readLine());

        int coin_cnt = 0;
//        coin_cnt += N / coin_500;
//        N %= coin_500;
//        coin_cnt += N / coin_100;
//        N %= coin_100;
//        coin_cnt += N / coin_50;
//        N %= coin_50;
//        coin_cnt += N / coin_10;

        // 개선 버전.
        int[] coin_arr = {500, 100, 50, 10};
        int len = coin_arr.length;
        for(int i = 0; i < len; i++){
            coin_cnt += N / coin_arr[i];
            N %= coin_arr[i];
        }

        System.out.println(coin_cnt);
    }
}
