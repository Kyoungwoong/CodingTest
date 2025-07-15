package ThisIsCodingTest.Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Failure {
    static class Stage implements Comparable<Stage> {
        int stage;
        double failure;

        public Stage(int stage, double failure) {
            this.stage = stage;
            this.failure = failure;
        }

        @Override
        public int compareTo(Stage s){
            if(this.failure > s.failure){
                return -1;
            }else if(this.failure < s.failure){
                return 1;
            }else{
                return this.stage - s.stage;
            }
        }

    }
    // 전체 스테이지 갯수
    private static int N, K;
    // 사용자가 멈춰있는 스테이지 번호
    private static int[] stages;
    private static HashMap<Integer, Integer> hashFailure = new HashMap<>();
    private static Stage[] ans;

    public static void main(String[] args) throws IOException {

//        prev();
        oct31();
    }

    private static void oct31() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        stages = new int[K];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            stages[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(stages);

        for (int i = 0; i < K; i++) {
            hashFailure.put(stages[i], hashFailure.getOrDefault(stages[i], 0) + 1);
        }

        ans = new Stage[N];
        for (int i = 1; i <= N; i++) {
            ans[i-1] = new Stage(i, hashFailure.getOrDefault(i, 0) / (double) K);
            K -= hashFailure.getOrDefault(i, 0);
        }

        Arrays.sort(ans);
        for(int i = 0 ; i < N; i++){
            System.out.println(ans[i].stage + " " + ans[i].failure);
        }
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        ans = new Stage[N];
        stages = new int[K];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            stages[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(stages);

        for (int i = 0; i < K; i++) {
            hashFailure.put(stages[i], hashFailure.getOrDefault(stages[i], 0) + 1);
        }

        for (int i = 1; i <= N; i++) {
            ans[i-1] = new Stage(i, hashFailure.getOrDefault(i, 0) / (double) K);
            K -= hashFailure.getOrDefault(i, 0);
        }

        Arrays.sort(ans);
        for(int i = 0 ; i < N; i++){
            System.out.println(ans[i].stage + " " + ans[i].failure);
        }
    }
}
/*
5
8
2 1 2 6 2 4 3 3
--- 3 4 2 1 5

4
5
4 4 4 4 4
--- 4 1 2 3
 */
