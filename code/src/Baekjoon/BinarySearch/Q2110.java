package Baekjoon.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Q2110{
    private static int N, C;
    private static int[] houses;

    public static void main(String[] args) throws IOException {

//        prev();
        may10();
    }

    private static void may10() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);

        // 첫번째 집에 공유기 설치.
        int count = 1, start = 1, end = houses[N - 1];
        int ans = -1;
        while (start <= end) {
            count = 1;
            int last = houses[0];
            int distance = (end + start) / 2;
            for (int i = 1; i < N; i++) {
                if (houses[i] >= last + distance) {
                    last = houses[i];
                    count++;
                }
            }

            if (count >= C) {
                ans = Math.max(ans, distance);
                start = distance + 1;
            } else {
                end = distance - 1;
            }
        }
        System.out.println(ans);
    }


//    public static int canInstall(int distance) {
//        int cnt = 1; // 첫번째 위치에 둔다.
//        int last = houses[0];
//        for (int i = 1; i < N; i++) {
//            if (houses[i] >= last + distance) {
//                last = houses[i];
//                cnt++;
//            }
//        }
//
//        return cnt;
//    }
//    private static void prev() throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        N = Integer.parseInt(st.nextToken());
//        C = Integer.parseInt(st.nextToken());
//
//        houses = new int[N];
//
//        for (int i = 0; i < N; i++) {
//            houses[i] = Integer.parseInt(br.readLine());
//        }
//        Arrays.sort(houses);
//
//        // 집의 위치를 기준으로 하는 것이 아닌. 최소 거리 기준으로 이분탐색.
//        int low = 1;
//        int high = houses[N-1] - houses[0];
//        int result = 0;
//
//        while (low <= high) {
//            int mid = (low + high) / 2;
//
//            if (canInstall(mid) < C) {
//                high = mid - 1;
//            }else{
//                low = mid + 1;
//                result = mid;
//            }
//        }
//        System.out.println(result);
//    }
}
/*
5 3
1
2
8
4
9
--- 3
 */

