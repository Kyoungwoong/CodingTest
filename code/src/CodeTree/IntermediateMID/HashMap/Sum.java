package CodeTree.IntermediateMID.HashMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Sum {
    private static int n, k, ans = 0;
    private static int[] arr = new int[100000];
    private static HashMap<Integer, Integer> sum = new HashMap<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());

        }

        for(int i = 0; i < n; i++){
            int diff = k - arr[i];
            if(sum.containsKey(diff)){
                ans += sum.get(diff);
            }

            sum.put(arr[i], sum.getOrDefault(arr[i], 0) + 1);

        }

        System.out.println(ans);

    }
}