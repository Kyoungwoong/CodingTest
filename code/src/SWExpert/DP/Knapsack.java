package SWExpert.DP;

import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

class Jew{
    int value, weight;
    public Jew(int weight, int value){
        this.value = value;
        this.weight = weight;
    }
}

public class Knapsack {
    private static int n, m, INT_MIN = Integer.MIN_VALUE;
    private static Jew[] jew = new Jew[100];
    // 무게가 i일때 보석의 최대 가치.
    private static int[] dp = new int[10001];

    public static void init(){
        for(int i = 0; i <= m; i++){
            dp[i] = INT_MIN;
        }
        dp[0] = 0;
    }

    public static void findMaxValue(){

        for(int i = 0; i < n; i++){
            for(int j = m; j >= 0; j--){
                if(j >= jew[i].weight){
                    if(dp[j - jew[i].weight] == INT_MIN) continue;
                    dp[j] = Math.max(dp[j], dp[j-jew[i].weight] + jew[i].value);
                }
            }
        }

    }

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        System.setIn(new FileInputStream("SW_Testcase/DP/knapsack.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());


        for(int test_case = 1; test_case <= T; test_case++) {
            dp = new int[10001];
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                jew[i] = new Jew(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            findMaxValue();

            int ans = -1;
            for(int i = 0; i <= m; i++){
                // System.out.print(dp[i] + " ");
                ans = Math.max(ans, dp[i]);
            }
            System.out.println("#" + test_case + " " + ans);

        }


    }
}