package ThisIsCodingTest.Binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Installation {
    private static int N, C;
    private static int[] houses;

    public static int canInstall(int distance) {
        int cnt = 1; // 첫번째 위치에 둔다.
        int last = houses[0];
        for (int i = 1; i < N; i++) {
            if (houses[i] >= last + distance) {
                last = houses[i];
                cnt++;
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        houses = new int[N];

        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(houses);

        // 집의 위치를 기준으로 하는 것이 아닌. 최소 거리 기준으로 이분탐색.
        int low = houses[0];
        int high = houses[N-1] - houses[0] + 1;
        while (low < high) {
            int mid = (low + high) / 2;

            if (canInstall(mid) < C) {
                high = mid;
            }else{
                low = mid + 1;
            }
        }
        System.out.println(low-1);
    }
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
