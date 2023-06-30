package ThisIsCodingTest.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Warrior {
    private static int N;
    private static int[] warriors;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        warriors = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            warriors[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        dp[0] = 1;
        int last = 0;
        int max = -1;
        for (int i = 1; i < N; i++) {
            if (warriors[i] < warriors[last]) {
                dp[i] = dp[last] + 1;
                last = i;
            }else{
                for(int j = 0; j < i; j++){
                    if (warriors[i] < warriors[j]) {
                        dp[i] = Math.max(dp[i], dp[j]);
                    }
                }
                dp[i] += 1;
                last = i;
            }
            max = Math.max(dp[i], max);
        }

        System.out.println(N - max);

        /*
        나동빈 버전
        static int n;
        static ArrayList<Integer> v = new ArrayList<Integer>();
        static int[] dp = new int[2000];

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            n = sc.nextInt();

            for (int i = 0; i < n; i++) {
                v.add(sc.nextInt());
            }

            // 순서를 뒤집어 '최장 증가 부분 수열' 문제로 변환
            Collections.reverse(v);

            // 다이나믹 프로그래밍을 위한 1차원 DP 테이블 초기화
            for (int i = 0; i < n; i++) {
                dp[i] = 1;
            }

            // 가장 긴 증가하는 부분 수열(LIS) 알고리즘 수행
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (v.get(j) < v.get(i)) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }

            // 열외해야 하는 병사의 최소 수를 출력
            int maxValue = 0;
            for (int i = 0; i < n; i++) {
                maxValue = Math.max(maxValue, dp[i]);
            }
            System.out.println(n - maxValue);
        }
         */
    }
}
/*
7
15 11 4 8 5 2 4
--- 2
 */
