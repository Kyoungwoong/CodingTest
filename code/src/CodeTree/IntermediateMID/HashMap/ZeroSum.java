package CodeTree.IntermediateMID.HashMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;

public class ZeroSum {
    private static int n;
    private static int[] arr = new int[5000];
    private static HashMap<Integer, Integer> AB = new HashMap<>();
    private static HashMap<Integer, Integer> CD = new HashMap<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // A
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // B -> AB
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            int num = Integer.parseInt(st.nextToken());
            for(int j = 0; j < n; j++){
                AB.put(num+arr[j], AB.getOrDefault(num+arr[j], 0) + 1);
            }
        }

        // C
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // D -> CD
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            int num = Integer.parseInt(st.nextToken());
            for(int j = 0; j < n; j++){
                CD.put(num+arr[j], CD.getOrDefault(num+arr[j], 0) + 1);
            }
        }

        int ans = 0;
        for(Integer ABSum: AB.keySet()){
            // for(Integer CDSum: CD.keySet()){
            //     if(ABSum + CDSum == 0){
            //         ans += AB.get(ABSum) * CD.get(CDSum);
            //     }
            // }
            if(CD.containsKey(-1 * ABSum)){
                ans += AB.get(ABSum) * CD.get(-1 * ABSum);
            }
        }
        System.out.println(ans);
    }
}