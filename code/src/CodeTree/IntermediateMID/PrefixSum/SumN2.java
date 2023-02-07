package CodeTree.IntermediateMID.PrefixSum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SumN2 {
    private static int n, k;
    private static int[] arr = new int[100001];

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
        }

        int ans = Integer.MIN_VALUE;
        for(int i = k; i <= n; i++){
            ans = Math.max(ans, arr[i]-arr[i-k]);
        }
        System.out.println(ans);
    }
}
