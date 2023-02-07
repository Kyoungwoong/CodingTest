package CodeTree.IntermediateMID.PrefixSum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SumK {
    private static int n, k;
    private static int[] arr = new int[1001];
    private static int[] sum = new int[1001];

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

        int cnt = 0;
        for(int i = 1; i <= n; i++){
            for(int j = i-1; j >= 0; j--){
                int ans = arr[i] - arr[j];
                if(ans == k){
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}