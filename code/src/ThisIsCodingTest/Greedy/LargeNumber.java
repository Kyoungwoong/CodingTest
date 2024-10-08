package ThisIsCodingTest.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class LargeNumber {
//    5 8 3
//            2 4 5 4 6
    public static int N, M, K;

    public static void main(String[] args) throws IOException {
//        prev();
        oct8();
    }

    private static void oct8() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int first = (M / (K + 1)) * K + M % (K + 1);
        int second = M - first;
        System.out.println(arr[N - 1] * first + arr[N - 2] * second);
    }

    private static void prev() throws IOException {
        // 연속해서 K번 초과 불가능.
        // M번 더해서 그 중에서 가장 큰 값.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Integer[] arr = new Integer[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        Arrays.sort(arr, Collections.reverseOrder());

        int idx = 0;
        while(M > 0){
            for(int i = 0; i < K; i++){
                result += arr[idx];
                M--;
            }
            result += arr[idx+1];
            M--;
        }

        /*  // 개선 버전

        int cnt = (M / (K+1)) * K; // 가장 큰 수가 더해지는 횟수
        cnt += M % (K+1); // 배수말고 나머지
        result += cnt * arr[0];
        result += (M-cnt) * arr[1];
         */

        System.out.println("result = " + result);
    }
}
