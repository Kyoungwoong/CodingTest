package CodeTree.IntermediateMID.PrefixSum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class RangeDotCnt {
    private static int n, q;
    private static int[] arr = new int[1000001];
    private static int[] sum = new int[1000001];

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            int num = Integer.parseInt(st.nextToken());
            max = Math.max(max, num);
            arr[num] = 1;
        }

        if(arr[0] == 1){
            sum[0] = 1;
        }
        for(int i = 1; i <= max; i++){
            if(arr[i] == 1){
                sum[i] = sum[i-1] + 1;
            }else{
                sum[i] = sum[i-1];
            }
        }
        for(int i = max+1; i <= 1000000; i++){
            sum[i] = sum[max];
        }

        for(int i = 0; i < q; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int ans;

            if(s-1 < 0){
                ans = sum[e];
            }else{
                ans = sum[e] - sum[s-1];
            }

            if(ans < 0){
                ans = 0;
            }
            sb.append(ans + "\n");
        }
        System.out.println(sb);


    }
}
