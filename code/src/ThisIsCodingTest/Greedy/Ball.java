package ThisIsCodingTest.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Ball {
    public static int N, M;
    public static ArrayList<Integer> balls = new ArrayList<>();

    public static void main(String[] args) throws IOException {
//        prev();
        oct28();
    }

    private static void oct28() throws IOException {
        // 두 사람은 서로 다른 무게의 볼링공
        // 서로다른 볼링공의 조합
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            balls.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(balls);

        int ans = 0;
        for (int i = 0; i < N; i++) {
            int A = balls.get(i);
            for (int j = i + 1; j < N; j++) {
                if (A != balls.get(j)) {
                    ans += N - j;
                    break;
                }
            }
        }

        System.out.println("ans = " + ans);
    }

    private static void prev() throws IOException {
        // 두 사람은 서로 다른 무게의 볼링공
        // 서로다른 볼링공의 조합
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            balls.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(balls);

        int cnt = 0;
        for(int i = 0; i < N; i++){
            int weight = balls.get(i);
            for(int j = i+1; j < N; j++){
                if(weight < balls.get(j)){
                    cnt += balls.size() - j;
                    break;
                }
            }
        }
        System.out.println("cnt = " + cnt);

        // version 나동빈
//        Scanner sc = new Scanner(System.in);
//        n = sc.nextInt();
//        m = sc.nextInt();
//
//        for (int i = 0; i < n; i++) {
//            int x = sc.nextInt();
//            arr[x] += 1;
//        }
//
//        int result = 0;
//
//        // 1부터 m까지의 각 무게에 대하여 처리
//        for (int i = 1; i <= m; i++) {
//            n -= arr[i]; // 무게가 i인 볼링공의 개수(A가 선택할 수 있는 개수) 제외
//            result += arr[i] * n; // B가 선택하는 경우의 수와 곱해주기
//        }
//
//        System.out.println(result);
    }
}
/*
5 3
1 3 2 3 2

8 5
1 5 4 3 2 4 5 2
 */
