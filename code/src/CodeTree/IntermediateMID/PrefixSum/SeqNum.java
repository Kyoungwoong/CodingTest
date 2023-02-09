package CodeTree.IntermediateMID.PrefixSum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SeqNum {
    private static int n, b, k;
    private static int[] arr = new int[100001];
    private static int[] sum = new int[100001];

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        for(int i = 0; i < b; i++){
            arr[Integer.parseInt(br.readLine())] = 1;
        }

        for(int i = 1; i <= n; i++){
            if(arr[i] == 0){
                sum[i] = sum[i-1] + 1;
            }else{
                sum[i] = sum[i-1];
            }
        }

        int ans = Integer.MAX_VALUE;
        for(int i = n; i >= k ; i--){
            ans = Math.min(ans, k - (sum[i] - sum[i-k]));
        }
        System.out.println(ans);
    }
}
