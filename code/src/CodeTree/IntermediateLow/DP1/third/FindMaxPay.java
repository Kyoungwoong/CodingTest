package CodeTree.IntermediateLow.DP1.third;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

class State implements Comparable<State>{
    int start, end, pay;

    public State(int s, int e, int p){
        start = s;
        end = e;
        pay = p;
    }

    @Override
    public int compareTo(State state){
        return this.start - state.start;
    }

}

public class FindMaxPay {
    private static int n, ans = -1;
    private static State[] Alba = new State[1000];
    // i번째 알바를 하였을 때 받을 수 있는 최대 금액.
    private static int[] dp = new int[1000];

    public static void init(){
        Arrays.sort(Alba, 0, n);

        for(int i = 0; i < n; i++){
            dp[i] = Alba[i].pay;
        }
    }

    public static void findMaxPay(){
        init();

        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                if(Alba[i].start > Alba[j].end){
                    dp[i] = Math.max(dp[i], dp[j] + Alba[i].pay);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            Alba[i] = new State(s, e, p);
        }

        findMaxPay();


        for(int i = 0; i < n; i++){
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }
}