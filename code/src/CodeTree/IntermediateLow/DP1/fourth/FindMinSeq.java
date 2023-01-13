package CodeTree.IntermediateLow.DP1.fourth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class FindMinSeq {

    private static int n, m, INT_MAX = Integer.MAX_VALUE;
    private static int[] arr = new int[100];
    private static int[] value = new int[10001];

    public static void init(){
        for(int i = 0; i <= m; i++){
            value[i] = INT_MAX;
        }
        value[0] = 0;
    }

    public static void findMinSeq(){
        for(int i = 0; i < n; i++){
            for(int j = m; j >= 0; j--){
                // 가능한 부분만 찾겠다.
                if(arr[i] <= j){
                    // 이전 공간에서는 이 값에 도달할 수 없음.
                    if(value[j - arr[i]] == INT_MAX) continue;
                    // 합이 M일 때 가장 작은 값 update
                    value[j] = Math.min(value[j], value[j - arr[i]] + 1);
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
            arr[i] = Integer.parseInt(st.nextToken());
        }

        init();

        findMinSeq();

        int ans = value[m];
        if(ans == INT_MAX){
            ans = -1;
        }
        System.out.println(ans);
    }
}
