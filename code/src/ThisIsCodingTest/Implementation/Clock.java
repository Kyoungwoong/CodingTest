package ThisIsCodingTest.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Clock {
    public static int hour;

    // 특정한 시각 안에 '3'이 포함되어 있는지의 여부
    public static boolean check(int h, int m, int s) {
        if (h % 10 == 3 || m / 10 == 3 || m % 10 == 3 || s / 10 == 3 || s % 10 == 3)
            return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        hour = Integer.parseInt(br.readLine());

        int ans = 0;

        for(int h = 0; h <= hour; h++){
            for(int m = 0; m <= 59; m++){
                for(int s = 0; s <= 59; s++){
                    String time = h+""+m+""+s;
                    int len = time.length();
                    for(int i = 0; i < len; i++){
                        if(time.charAt(i) == '3'){
//                            System.out.println("time = " + time);
                            ans++;
                            // 생각을 조금 더 하자. 이게 왜 필요한지.
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("ans = " + ans);
        /*
         version. 나동빈
         Scanner sc = new Scanner(System.in);

        // H를 입력받기
        int h = sc.nextInt();
        int cnt = 0;

        for (int i = 0; i <= h; i++) {
            for (int j = 0; j < 60; j++) {
                for (int k = 0; k < 60; k++) {
                    // 매 시각 안에 '3'이 포함되어 있다면 카운트 증가
                    if (check(i, j, k)) cnt++;
                }
            }
        }
         */

    }
}
