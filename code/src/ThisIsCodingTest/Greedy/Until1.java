package ThisIsCodingTest.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.cert.TrustAnchor;
import java.util.StringTokenizer;

public class Until1 {
//    25 5 // ans: 2
//    25 3 // ans: 6
    public static int cnt = 0, N, K;
    public static void main(String[] args) throws IOException {
//        prev();
        oct8();
    }

    private static void oct8() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        while (N != 1 && N >= K) {
            if (N % K != 0) {
                cnt += N % K;
                N -= (N % K);
            } else {
                cnt++;
                N /= K;
            }
        }

        if (N != 1) {
            cnt += (N - 1);
        }

        System.out.println("cnt = " + cnt);
    }

    private static void prev() throws IOException {
        // N에서 1을 뺀다
        // N을 K로 나눈다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        while(N != 1){
            if(N % K == 0){
                N /= K;
                cnt++;
            }else{
                int add = N % K;
                N -= add;
                if(N == 0){
                    N++;
                    add -= 1;
                }
                cnt += add;
            }
            System.out.println("N = " + N);
        }
        System.out.println("cnt = " + cnt);
        /*
         예외 케이스 생각 잘하기.
         */

        // 정답코드
        // version 1
//        while(N >= K){
//            while(N % K != 0){
//                N -= 1;
//                cnt += 1;
//            }
//            N /= K;
//            cnt += 1;
//        }
//        while(N > 1){
//            N -= 1;
//            cnt += 1;
//        }

        // version 2
//        while(true){
//            int target = (N / K) * K;
//            cnt += (N - target);
//            N = target;
//            if( N < K){
//                break;
//            }
//            cnt += 1;
//            N /= K;
//        }
//        cnt += (N-1);
    }
}
