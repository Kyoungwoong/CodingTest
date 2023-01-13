package CodeTree.IntermediateLow.DP1.fourth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class FindMinCoin {
    private static int n, m, INT_MAX = Integer.MAX_VALUE;
    private static int[] coin = new int[101];
    private static int[] value = new int[10001];

    public static void init(){
        for(int i = 0; i <= m; i++){
            value[i] = INT_MAX;
        }
        value[0] = 0;
    }

    public static void findMinCoin(){
        for(int i = 0; i <= m; i++){
            for(int j = 0; j < n; j++){
                if(i >= coin[j]){
                    if(value[i-coin[j]] == INT_MAX) continue;
                    value[i] = Math.min(value[i], value[i-coin[j]] + 1);
                }

            }

        }
    }

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            coin[i] = Integer.parseInt(st.nextToken());
        }

        init();

        findMinCoin();

        if(value[m] == INT_MAX){
            System.out.println(-1);
        }else{
            System.out.println(value[m]);
        }
    }
}
