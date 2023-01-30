package CodeTree.IntermediateMID.HashMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;

public class SumThree {
    private static int n, k;
    private static int[] arr = new int[1000];
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
            // sum.put(arr[i], 1);
            sum.put(arr[i], sum.getOrDefault(arr[i], 0) + 1);
        }

        int ans = 0;
        for(int i = 0; i < n; i++){

            sum.put(arr[i], sum.get(arr[i])-1);


            for(int j = 0; j < i; j++){
                int diff = k - (arr[i] + arr[j]);

                if(sum.containsKey(diff)){
                    System.out.println(" arr[i]: " + arr[i]+"("+(i)+")" + " arr[j]: " + arr[j]+"("+(j)+")"+ " diff: " + diff);
                    ans += sum.get(diff);
                }
            }
        }
        System.out.println(ans);
    }
}
