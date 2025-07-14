package ThisIsCodingTest.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Warrior {
    private static int N;
    private static int[] warriors;

    public static void main(String[] args) throws IOException {
//        prev();
        nov1();
    }

    private static void nov1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        List<Integer> warriorsList = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            warriorsList.add(Integer.parseInt(st.nextToken()));
        }
//        Collections.reverse(warriorsList);

        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (warriorsList.get(j) > warriorsList.get(i)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxValue = 0;
        for (int i = 0; i < N; i++) {
            System.out.print(dp[i] + " ");
            maxValue = Math.max(maxValue, dp[i]);
        }
        System.out.println();
        System.out.println(N - maxValue);
    }

    private static void prev() throws IOException {
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
    }

    private static void nov1_Optimized() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] warriors = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            warriors[i] = Integer.parseInt(st.nextToken());
        }

        // LIS 구하기 위해 배열 뒤집기
        int[] reversed = new int[N];
        for (int i = 0; i < N; i++) {
            reversed[i] = warriors[N - 1 - i];
        }

        List<Integer> dp = new ArrayList<>();
        for (int x : reversed) {
            int idx = lowerBound(dp, x);
            if (idx == dp.size()) {
                dp.add(x);
            } else {
                dp.set(idx, x);
            }
        }

        System.out.println(N - dp.size());
    }

    // lower_bound: dp에서 처음으로 x 이상이 되는 위치를 찾는 함수
    private static int lowerBound(List<Integer> list, int target) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // lower_bound: target 이상인 가장 왼쪽 인덱스
    public static int lowerBound(int[] arr, int target) {
        int left = 0, right = arr.length;

        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] < target) {
                left = mid + 1;  // target보다 작으면 버림
            } else {
                right = mid;     // target 이상이면 mid도 가능성이 있음
            }
        }
        return left;  // 또는 right
    }

    // upper_bound: target 초과인 가장 왼쪽 인덱스
    public static int upperBound(int[] arr, int target) {
        int left = 0, right = arr.length;

        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] <= target) {
                left = mid + 1;  // target 이하이므로 mid는 제외
            } else {
                right = mid;     // target 초과 → 후보
            }
        }
        return left;  // 또는 right
    }
}
/*
7
15 11 4 8 5 2 4
--- 2
 */


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