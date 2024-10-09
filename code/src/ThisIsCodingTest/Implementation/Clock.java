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
//        prev();
        oct9();

    }

    private static void oct9() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int h = 0;
        int ans = 0;
        while (h <= n) {
            if (h == 3 || h == 13 || h == 23) {
                ans += 60 * 60; // 시에 3이 들어가 분 * 초
            } else {
                ans += ((3600 - 2025)); // 시가 3이 아닐 때 3이 들어가는거 여집합으로 구하기
            }
            System.out.println("if h: " + h + " => ans: " + ans);
            h++;
        }
        System.out.println("ans = " + ans);
    }

    private static void prev() throws IOException {
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
